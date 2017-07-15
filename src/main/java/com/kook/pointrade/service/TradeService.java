package com.kook.pointrade.service;

import com.kook.pointrade.constants.AppConstants;
import com.kook.pointrade.dao.BasketDAO;
import com.kook.pointrade.dao.CurrentRateDAO;
import com.kook.pointrade.dao.PtradeDAO;
import com.kook.pointrade.dao.TotalTradeDAO;
import com.kook.pointrade.domain.CurrentRateDTO;
import com.kook.pointrade.domain.PbasketDTO;
import com.kook.pointrade.domain.PtradeDTO;
import com.kook.pointrade.domain.TotalTradeDTO;
import com.kook.pointrade.service.object.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TradeService {


	@Autowired
	TotalTradeDAO totalTradeDAO;

	@Autowired
	CurrentRateDAO currentRateDAO;

	@Autowired
	PtradeDAO ptradeDAO;

	@Autowired
	BasketDAO basketDAO;

	public Map<String, Object> getTradeListAndCurrentRate(int fromPoint, int toPoint){
		Map<String, Object> rMap = new HashMap<String, Object>();

		//order by rate desc
		List<TotalTradeDTO> list = totalTradeDAO.selectTradeByPointKey(fromPoint, toPoint);
//		rMap.put("tradeList", list);

		CurrentRateDTO current = currentRateDAO.selectCurrentRateByPk(fromPoint, toPoint);
		Rate currentRate = new Rate(current.getCurrentRateSon(),current.getCurrentRateMom());
		rMap.put("currentRate", currentRate);

		if(list.size()==0){
			rMap.put("tradeList",null);
			return rMap;
		}

		//currentRate 기준으로 필요한 만큼만 잘라서 넘겨주기
		int listSize = list.size();
		int frontSize=0, backSize=0;
		for( int index = 0; index < listSize; index++ ) {
			TotalTradeDTO t = list.get(index);
			if(currentRate.equals(new Rate(t.getRateSon(), t.getRateMom()))){
				if("01".equals(t.getTradeCode())){
					frontSize = index+1;
					backSize = listSize-frontSize;
				}else{
					frontSize = index;
					backSize = listSize-frontSize;
				}
			}
		}
		int middleNumber = AppConstants.HTML_TABLE_ROWS/2;
		int fromIndex = (frontSize>middleNumber) ? frontSize-middleNumber : 0;
		int toIndex = (backSize>middleNumber) ? listSize-(backSize-middleNumber): listSize;
		rMap.put("tradeList", list.subList(fromIndex, toIndex));

		rMap.put("frontSize", frontSize);
		rMap.put("backSize", backSize);
		return rMap;
		
	}



	public Map sellPoint(int userKey, int fromPoint, int toPoint, BigDecimal rateSon, BigDecimal rateMom, int amount, String tradeCode){
		Map<String, Object> rMap = new HashMap<String, Object>();
		rMap.put("isSuccess", true);

		CurrentRateDTO cRateDTO = currentRateDAO.selectCurrentRateByPk(fromPoint, toPoint);
		Rate currentRate = new Rate(cRateDTO.getCurrentRateSon(),cRateDTO.getCurrentRateMom());
		Rate sellRate = null, buyRate = null;
		if(AppConstants.SELLING.equals(cRateDTO.getTradeCode())){
			sellRate = currentRate;
			buyRate = currentRate.down();
		}else{
			sellRate = currentRate.up();
			buyRate = currentRate;
		}

		Rate rateToSell = new Rate(rateSon, rateMom);
		TotalTradeDTO currentTotal = totalTradeDAO.selectTradeByPk(fromPoint, toPoint, rateToSell);
		if(currentTotal==null){
			currentTotal = totalTradeDAO.insertTrade(fromPoint, toPoint, rateToSell, AppConstants.SELLING, 0);
			totalTradeDAO.insertTrade(toPoint, fromPoint, rateToSell.upsideDown(), AppConstants.SELLING, 0);
		}

		if(AppConstants.SELLING.equals(tradeCode)){
			//매도세
			// totalTrade에 매도 물량 추가
			//TODO TO,FROM으로도!
			int calculatedAmount = currentTotal.getAmount()+amount;
			totalTradeDAO.updateTrade(fromPoint, toPoint, rateToSell, AppConstants.SELLING, calculatedAmount);
			totalTradeDAO.updateTrade(toPoint, fromPoint, rateToSell.upsideDown(), AppConstants.BUYING, rateToSell.adjustRate(calculatedAmount));

			// currentRate를 매도세 경계선에
			//TODO TO,FROM으로도!
			currentRateDAO.updateCurrentRate(fromPoint, toPoint, AppConstants.SELLING, sellRate);
			currentRateDAO.updateCurrentRate(toPoint, fromPoint, AppConstants.BUYING, sellRate.upsideDown());

			// 현사용자 ptrade 정보 (from, to, rate) 있으면 update, 아니면 insert
			PtradeDTO ptradeDTO = ptradeDAO.selectTradeByPk(userKey, fromPoint, toPoint, rateToSell);
			if(ptradeDTO != null){
				ptradeDAO.updateTradeByPk(userKey, fromPoint, toPoint, rateToSell, AppConstants.SELLING, calculatedAmount);
			}else{
				ptradeDAO.insertTrade(userKey, fromPoint, toPoint, rateToSell, AppConstants.SELLING, calculatedAmount);
			}

		}else{
			//매수세
			// currentRate 조회해서, 매수 경계인지 확인
			int myBasket = 0;
			if(currentRate.equals(rateToSell)
					&& amount>=currentTotal.getAmount()){
				//매수 경계일때만 매수물량 이상 매도할 수 있도록
				// totalTrade에 매수 물량 빼기, 매도로 돌리기
				myBasket = currentTotal.getAmount();
				int calculatedAmount =amount-currentTotal.getAmount();
						//TODO TO,FROM으로도!
				totalTradeDAO.updateTrade(fromPoint, toPoint, rateToSell, AppConstants.SELLING, calculatedAmount);
				totalTradeDAO.updateTrade(toPoint, fromPoint, rateToSell.upsideDown(), AppConstants.BUYING, rateToSell.adjustRate(calculatedAmount));
				// currentRate를 새로 만들어질 매도세 경계선에
				//TODO TO,FROM으로도!
				currentRateDAO.updateCurrentRate(fromPoint, toPoint,AppConstants.SELLING, rateToSell);
				currentRateDAO.updateCurrentRate(toPoint, fromPoint,AppConstants.BUYING, rateToSell.upsideDown());

				// 현사용자 ptrade 정보 (from, to, rate) 있으면 update, 아니면 insert
				PtradeDTO ptradeDTO = ptradeDAO.selectTradeByPk(userKey, fromPoint, toPoint, rateToSell);
				if(ptradeDTO != null){
					ptradeDAO.updateTradeByPk(userKey, fromPoint, toPoint, rateToSell, AppConstants.SELLING, calculatedAmount);
				}else{
					ptradeDAO.insertTrade(userKey, fromPoint, toPoint, rateToSell, AppConstants.SELLING, calculatedAmount);
				}

			}else{
				myBasket = amount;
				int calculatedAmount = currentTotal.getAmount()-amount;
				//TODO TO,FROM으로도!
				// totalTrade에 매수 물량 빼기
				totalTradeDAO.updateTrade(fromPoint, toPoint, rateToSell, AppConstants.BUYING, calculatedAmount);
				totalTradeDAO.updateTrade(toPoint, fromPoint, rateToSell.upsideDown(), AppConstants.SELLING, rateToSell.adjustRate(calculatedAmount));

				// currentRate를 매수세 경계선에
				currentRateDAO.updateCurrentRate(fromPoint, toPoint,AppConstants.BUYING, buyRate);
				currentRateDAO.updateCurrentRate(toPoint, fromPoint,AppConstants.SELLING, buyRate.upsideDown());
			}

			// 빠진 물량 관련 Ptrade 조회해서 빼기
			// Ptrade 빠지는 사용자들 basket에 교환된 포인트(수수료 떼고)를 넣어줌
			//TODO from, to 반대로 해서 SELLING을 없애는 식ㅇ로
			selectAndUpdateOtherPtrades(toPoint, fromPoint, rateToSell.upsideDown(), rateToSell.adjustRate(amount));

			//TODO 거래가 된거니까 내 basket에도 넣어줘야지!!
			PbasketDTO basket = basketDAO.selectBasketByUserPoint(userKey, toPoint);
			basketDAO.updatePbasketByUserPoint(userKey, toPoint, basket.getBalance()+myBasket);


		}

		//TODO 현사용자 basket에서 빠져나감
		PbasketDTO basket = basketDAO.selectBasketByUserPoint(userKey, fromPoint);
		basketDAO.updatePbasketByUserPoint(userKey, fromPoint, basket.getBalance()-amount);
		return rMap;
	}



//	private int updatePtradeByUserAndPointAndRate(int userKey, int fromPoint, int toPoint, Rate rate,
//												  String tradeCode,  int amount){
//
//		//select 해보고
//		PtradeDTO ptrade = ptradeDAO.selectTradeByPk(userKey, fromPoint, toPoint, rate.getRateSon(), rate.getRateMom());
//
//		if(ptrade.getTimeMillis()!=0L){
//			// 있으면 update,
//			// existTradeCode와 같으면 existAmount+amount
//			return ptradeDAO.updateTradeByPk(userKey, fromPoint, toPoint, rate, tradeCode, ptrade.getAmount()+amount);
//
//		}else{
//			// 없으면 insert
//			return this.insertPtrade(userKey, fromPoint, toPoint, rate, tradeCode, amount);
//
//		}
//	}
//	private int insertPtrade(int userKey, int fromPoint, int toPoint,
//							 BigDecimal rate, String tradeCode, int amount){
//		return ptradeDAO.insertTrade(userKey, fromPoint, toPoint, rate, tradeCode, amount);
//	}
//
	private int selectAndUpdateOtherPtrades(int fromPoint, int toPoint, Rate rate, int amount){

		List<PtradeDTO> list = ptradeDAO.selectPtradesByPointRate(fromPoint, toPoint, rate, AppConstants.SELLING);
		for(PtradeDTO pDTO : list) {
			int existBalance = basketDAO.selectBasketByUserPoint(pDTO.getUserKey(), pDTO.getToPointKey()).getBalance();

			if(pDTO.getAmount() >= amount){
				ptradeDAO.updateTradeByPk(pDTO.getUserKey(), fromPoint, toPoint, rate, AppConstants.SELLING, pDTO.getAmount()-amount);
				//TODO BASKET에 교환된 포인트를 넣어줘야함
				basketDAO.updatePbasketByUserPoint(pDTO.getUserKey(), pDTO.getToPointKey(),existBalance+amount);
				break;
			}else{
				ptradeDAO.deletePtradeByPk(pDTO.getUserKey(), fromPoint, toPoint, rate);
				//TODO BASKET에 교환된 포인트를 넣어줘야함
				basketDAO.updatePbasketByUserPoint(pDTO.getUserKey(), pDTO.getToPointKey(),existBalance+pDTO.getAmount());
				amount -= pDTO.getAmount();

			}
		}
		return 1;
	}
}
