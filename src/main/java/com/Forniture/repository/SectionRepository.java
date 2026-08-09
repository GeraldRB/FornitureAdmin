
package com.Forniture.repository;

import com.Forniture.domain.Section;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepository extends JpaRepository<Section, Integer>{
    
    List<Section> findBySectionByte(Byte sectionByte);
    
}
