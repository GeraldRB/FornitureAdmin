
package com.Forniture.service;

import com.Forniture.domain.Section;
import com.Forniture.repository.SectionRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SectionService {
    
    private SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }
    

    @Transactional(readOnly = false)
    public List<Section> getBySection(Byte sectionByte){
        return sectionRepository.findBySectionByte(sectionByte);
    }
    
}
