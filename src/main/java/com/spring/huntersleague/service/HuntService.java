package com.spring.huntersleague.service;

import com.spring.huntersleague.domain.Hunt;
import com.spring.huntersleague.repository.HuntRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class HuntService {

    private final HuntRepository huntRepository;

    public HuntService(HuntRepository huntRepository) {
        this.huntRepository = huntRepository;
    }

    public Hunt createHunt(Hunt hunt) {
        return huntRepository.save(hunt);
    }

    public Hunt updateHunt(Hunt hunt) {
        return huntRepository.save(hunt);
    }

    public Optional<Hunt> getHuntById(UUID id) {
        return huntRepository.findById(id);
    }

    public void deleteHunt(UUID id) {
        huntRepository.deleteById(id);
    }

    public Page<Hunt> findAllHunts(Pageable pageable) {
        return huntRepository.findAll(pageable);
    }

    public Long count(){
        return huntRepository.count();
    }

}
