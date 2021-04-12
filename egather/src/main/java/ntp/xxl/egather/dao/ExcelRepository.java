package ntp.xxl.egather.dao;

import ntp.xxl.egather.po.Excel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExcelRepository extends JpaRepository<Excel,Long> {
    List<Excel> findAllByUserId(Long id);
    List<Excel> findAll();
    Excel getById(Long id);
    Excel save(Excel excel);
    void deleteById(Long id);
}
