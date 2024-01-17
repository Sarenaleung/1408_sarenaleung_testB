package com.example.Q5.api;

import org.springframework.stereotype.Service;

import com.example.Q5.model.CrimeRecord;
import com.example.Q5.repository.CrimeRecordRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrimeRecordService {

    private final CrimeRecordRepository crimeRecordRepository;

    public CrimeRecordService(CrimeRecordRepository crimeRecordRepository) {
        this.crimeRecordRepository = crimeRecordRepository;
    }

    public List<CrimeRecordResponse> searchByDateRange(LocalDateTime from, LocalDateTime to) {
        return crimeRecordRepository.findByDateOccBetween(from, to)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CrimeRecordResponse convertToDto(CrimeRecord entity) {
        CrimeRecordResponse dto = new CrimeRecordResponse();
        // convert CrimeRecord data into the response object
        if (entity.getDrNo() != null) {
            dto.setDrNo(entity.getDrNo().toString());
        }

        if (entity.getDateRptd() != null) {
            dto.setDateRptd(entity.getDateRptd().toString());
        }

        if (entity.getDateOcc() != null) {
            dto.setDateOcc(entity.getDateOcc().toString());
        }

        if (entity.getTimeOcc() != null) {
            dto.setTimeOcc(entity.getTimeOcc().toString());
        }

        if (entity.getAreaCd() != null && entity.getAreaCd().getArea() != null) {
            dto.setArea(entity.getAreaCd().getArea().toString());
        }

        if (entity.getAreaCd() != null) {
            dto.setAreaName(entity.getAreaCd().getAreaName());
        }

        if (entity.getRptDistNo() != null) {
            dto.setRptDistNo(entity.getRptDistNo().toString());
        }

        if (entity.getCrimeCode() != null) {
            dto.setCrmCdDesc(entity.getCrimeCode().getCrmCdDesc());
        }

        if (entity.getVictim() != null && entity.getVictim().getAge() != null) {
            dto.setVictAge(entity.getVictim().getAge().toString());
        }

        if (entity.getVictim() != null) {
            dto.setVictSex(entity.getVictim().getSex());
            dto.setVictDescent(entity.getVictim().getDescent());
        }

        if (entity.getPremisCode() != null && entity.getPremisCode().getPremisCd() != null) {
            dto.setPremisCd(entity.getPremisCode().getPremisCd().toString());
        }

        if (entity.getPremisCode() != null) {
            dto.setPremisDesc(entity.getPremisCode().getPremisDesc());
        }

        if (entity.getWeapon() != null && entity.getWeapon().getWeaponCd() != null) {
            dto.setWeaponUsedCd(entity.getWeapon().getWeaponCd().toString());
        }

        if (entity.getWeapon() != null) {
            dto.setWeaponDesc(entity.getWeapon().getWeaponDesc());
        }

        if (entity.getStatus() != null) {
            dto.setStatus(entity.getStatus().getStatus());
            dto.setStatusDesc(entity.getStatus().getStatusDesc());
        }

        return dto;
    }
}
