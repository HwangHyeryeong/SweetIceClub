package app.mvc.service;

import java.util.List;

import app.mvc.dao.ManagerDAO;
import app.mvc.dao.ManagerDAOImpl;
import app.mvc.dto.ItemDTO;
import app.mvc.dto.MemberDTO;
import app.mvc.dto.OrderDTO;
import app.mvc.exception.DMLException;
import app.mvc.exception.SearchWrongException;

public class ManagerServiceImpl implements ManagerService {
	private static ManagerService instance = new ManagerServiceImpl();
	
	private ManagerDAO managerDao = ManagerDAOImpl.getInstance();
	
	private ManagerServiceImpl() {}
	
	public static ManagerService getInstance() {
		return instance;
	}

	@Override
	public List<OrderDTO> selectOrderAll() throws SearchWrongException {
		List<OrderDTO> list = managerDao.selectOrderAll();
		if (list.isEmpty()) {
			throw new SearchWrongException(" 주문내역에 주문 정보가 없습니다.");
		}
		return list;
	}

	@Override
	public int selectTotalSalesByPeriod(int period) throws SearchWrongException {
		int result = managerDao.selectTotalSalesByPeriod(period);
		if (result == 0) {
			throw new SearchWrongException("해당기간 동안 주문 정보가 없습니다.");
		}
		return result;
	}

	@Override
	public List<ItemDTO> selectItemAll() throws SearchWrongException {
		List<ItemDTO> list = managerDao.selectItemAll();
		if (list.isEmpty()) {
			throw new SearchWrongException("아이스크림 정보가 없습니다.");
		}
		return list;
	}

	@Override
	public List<String> selectItemTop3() throws SearchWrongException {
		List<String> list = managerDao.selectItemTop3();
		if (list.isEmpty()) {
			throw new SearchWrongException("Top3 아이스크림 정보가 없습니다.");
		}
		return list;
	}

	@Override
	public int insertItem(ItemDTO itemDTO) throws DMLException {
		int result = managerDao.insertItem(itemDTO);
		if (result == 0) {
			throw new SearchWrongException("아이스크림이 추가되지 않았습니다.");
		}
		return result;
	}

	@Override
	public int deleteItemByItemName(String itemName) throws DMLException {
		int result = managerDao.deleteItemByItemName(itemName);
		if (result == 0) {
			throw new SearchWrongException(itemName + "아이스크림이 삭제되지 않았습니다.");
		}
		return result;
	}

	@Override
	public int updateItemStock(ItemDTO itemDTO) throws DMLException {
		int result = managerDao.updateItemStock(itemDTO);
		if (result == 0) {
			throw new DMLException(itemDTO.getItemName() + " 아이스크림 재고가 수정되지 않았습니다.^^");
		}
		return result;
	}
}


