package duksung.backend.hororok.ugeubi.fcmserver.service;

import duksung.backend.hororok.ugeubi.fcmserver.domain.repository.DeviceTokenRepository;
import duksung.backend.hororok.ugeubi.fcmserver.dto.DeviceTokenDto;
import duksung.backend.hororok.ugeubi.taking.domain.repository.TakingHistoryRepository;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistorySaveRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingHistoryUpdateIstakenRequestDTO;
import duksung.backend.hororok.ugeubi.taking.dto.TakingInfoHistoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;

    //디바이스 토큰 저장
    @Transactional
    public Long save(DeviceTokenDto deviceTokenDto){
        return deviceTokenRepository.save(deviceTokenDto.toEntity()).getId();
    }

}
