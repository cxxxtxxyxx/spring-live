package shop.mtcoding.bankapp.model.history;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import shop.mtcoding.bankapp.dto.history.HistoryResponseDto;

@Mapper
public interface HistoryRepository {


    List<HistoryResponseDto> findByGubun(@Param("gubun") String gubun, @Param("accountId") int accountId);

    public int insert(History history);

    public int updateById(History history);

    public int deleteById(int id);

    public List<History> findAll();

    public History findById(int id);
}
