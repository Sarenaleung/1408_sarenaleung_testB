package com.example.Q5.batch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import com.example.Q5.model.Area;
import com.example.Q5.model.CrimeCode;
import com.example.Q5.model.CrimeRecord;
import com.example.Q5.model.PremisCode;
import com.example.Q5.model.Status;
import com.example.Q5.model.Victim;
import com.example.Q5.model.Weapon;
import com.example.Q5.repository.AreaRepository;
import com.example.Q5.repository.CrimeCodeRepository;
import com.example.Q5.repository.PremisCodeRepository;
import com.example.Q5.repository.StatusRepository;
import com.example.Q5.repository.VictimRepository;
import com.example.Q5.repository.WeaponRepository;

public class CrimeRecordProcessor implements ItemProcessor<CSVRecordDTO, CrimeRecord> {

    private final AreaRepository areaRepository;
    private final CrimeCodeRepository crimeCodeRepository;
    private final PremisCodeRepository premisCodeRepository;
    private final StatusRepository statusRepository;
    private final VictimRepository victimRepository;
    private final WeaponRepository weaponRepository;

    public CrimeRecordProcessor(CrimeCodeRepository crimeCodeRepository, AreaRepository areaRepository,
            PremisCodeRepository premisCodeRepository, StatusRepository statusRepository,
            WeaponRepository weaponRepository, VictimRepository victimRepository) {
        this.crimeCodeRepository = crimeCodeRepository;
        this.areaRepository = areaRepository;
        this.premisCodeRepository = premisCodeRepository;
        this.statusRepository = statusRepository;
        this.weaponRepository = weaponRepository;
        this.victimRepository = victimRepository;
    }

    @Override
    public CrimeRecord process(final CSVRecordDTO csvRecordDTO) throws Exception {

        CrimeRecord crimeRecord = new CrimeRecord();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH);

        // Set the properties of the crimeRecord object using the data from CSVRecordDTO
        String drNoString = csvRecordDTO.getDrNo();
        crimeRecord.setDrNo(drNoString != "" ? Integer.parseInt(drNoString) : null);

        String dateRptdString = csvRecordDTO.getDateRptd();
        crimeRecord.setDateRptd(dateRptdString != "" ? LocalDateTime.parse(dateRptdString, formatter) : null);

        String dateOccString = csvRecordDTO.getDateOcc();
        crimeRecord.setDateOcc(dateOccString != "" ? LocalDateTime.parse(dateOccString, formatter) : null);

        String timeOccString = csvRecordDTO.getTimeOcc();
        crimeRecord.setTimeOcc(timeOccString != "" ? Integer.parseInt(timeOccString) : null);

        String rptDistNoString = csvRecordDTO.getRptDistNo();
        crimeRecord.setRptDistNo(rptDistNoString != "" ? Integer.parseInt(rptDistNoString) : null);

        String part12String = csvRecordDTO.getPart12();
        crimeRecord.setPart1Or2(part12String != "" ? Integer.parseInt(part12String) : null);

        crimeRecord.setMocodes(csvRecordDTO.getMocodes());

        String crmCd1String = csvRecordDTO.getCrmCd1();
        crimeRecord.setCrmCd1(crmCd1String != "" ? (int) Float.parseFloat(crmCd1String) : null);

        String crmCd2String = csvRecordDTO.getCrmCd2();
        crimeRecord.setCrmCd2(crmCd2String != "" ? (int) Float.parseFloat(crmCd2String) : null);

        String crmCd3String = csvRecordDTO.getCrmCd3();
        crimeRecord.setCrmCd3(crmCd3String != "" ? (int) Float.parseFloat(crmCd3String) : null);

        String crmCd4String = csvRecordDTO.getCrmCd4();
        crimeRecord.setCrmCd4(crmCd4String != "" ? (int) Float.parseFloat(crmCd4String) : null);

        crimeRecord.setLocation(csvRecordDTO.getLocation());
        crimeRecord.setCrossStreet(csvRecordDTO.getCrossStreet());

        String latString = csvRecordDTO.getLat();
        crimeRecord.setLat(latString != "" ? Float.parseFloat(latString) : null);

        String lonString = csvRecordDTO.getLon();
        crimeRecord.setLon(lonString != "" ? Float.parseFloat(lonString) : null);

        String areaString = csvRecordDTO.getArea();
        crimeRecord.setArea(areaString != "" ? Float.parseFloat(areaString) : null);


        Integer areaCd = csvRecordDTO.getAreaCd() != "" && csvRecordDTO.getAreaCd() != null
                ? Integer.parseInt(csvRecordDTO.getAreaCd())
                : null;
        String areaName = csvRecordDTO.getAreaName() != "" ? csvRecordDTO.getAreaName() : null;
        if (areaCd != null) {
            Area area = new Area(areaCd, areaName);
            // Save area if it doesn't exist in DB (use AreaRepository)
            if (!areaRepository.existsById(area.getArea()))
                areaRepository.save(area);

            crimeRecord.setAreaCd(area);
        }


        Integer crmCd = csvRecordDTO.getCrmCd() != "" ? Integer.parseInt(csvRecordDTO.getCrmCd()) : null;
        String crmCdDesc = csvRecordDTO.getCrmCdDesc() != "" ? csvRecordDTO.getCrmCdDesc() : null;
        if (crmCd != null) {
            CrimeCode crimeCode = new CrimeCode(crmCd, crmCdDesc);
            // Save crimeCode if it doesn't exist in DB
            if (!crimeCodeRepository.existsById(crimeCode.getCrmCd())) {
                crimeCodeRepository.save(crimeCode);
            }

            crimeRecord.setCrimeCode(crimeCode);
        }


        Integer permisCd = csvRecordDTO.getPremisCd() != "" ? (int) Float.parseFloat(csvRecordDTO.getPremisCd()) : null;
        String premisDesc = csvRecordDTO.getPremisDesc() != "" ? csvRecordDTO.getPremisDesc() : null;
        if (permisCd != null) {
            PremisCode premisCode = new PremisCode(permisCd, premisDesc);
            // Save premisCode if it doesn't exist in DB
            if (!premisCodeRepository.existsById(premisCode.getPremisCd())) {
                premisCodeRepository.save(premisCode);
            }

            crimeRecord.setPremisCode(premisCode);
        }


        String statusCd = csvRecordDTO.getStatus() != null && !csvRecordDTO.getStatus().isEmpty()
                ? csvRecordDTO.getStatus()
                : null;
        String statusDesc = csvRecordDTO.getStatusDesc() != null && !csvRecordDTO.getStatusDesc().isEmpty()
                ? csvRecordDTO.getStatusDesc()
                : null;
        if (statusCd != null) {
            if (!statusRepository.existsByStatus(statusCd)) {
                Status status = new Status(statusCd, statusDesc);
                statusRepository.save(status);
            }
            // Fetch the status entity to set in crimeRecord
            Status status = statusRepository.findById(statusCd).orElse(null);
            crimeRecord.setStatus(status);
        }


        Integer weaponCd = csvRecordDTO.getWeaponUsedCd() != "" ? (int) Float.parseFloat(csvRecordDTO.getWeaponUsedCd())
                : null;
        String weaponDesc = csvRecordDTO.getWeaponDesc() != "" ? csvRecordDTO.getWeaponDesc() : null;
        if (weaponCd != null) {
            Weapon weapon = new Weapon(weaponCd, weaponDesc);
            // Save weapon if it doesn't exist in DB
            if (!weaponRepository.existsById(weapon.getWeaponCd())) {
                weaponRepository.save(weapon);
            }

            crimeRecord.setWeapon(weapon);
        }

        
        Integer victimAge = csvRecordDTO.getVictimAge() != "" ? Integer.parseInt(csvRecordDTO.getVictimAge())
                : null;
        String victimSex = csvRecordDTO.getVictimSex();

        String victimDescent = csvRecordDTO.getVictimDescent();

        if (victimAge != null || victimSex != null || victimDescent != null) {
            Victim victim = new Victim(Integer.parseInt(csvRecordDTO.getVictimAge()), csvRecordDTO.getVictimSex(),
                    csvRecordDTO.getVictimDescent());

            // Check if victim already exists in DB
            ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnoreNullValues();
            Example<Victim> example = Example.of(victim, exampleMatcher);
            boolean victimExists = victimRepository.exists(example);

            if (!victimExists) {
                victimRepository.save(victim);
            }

            crimeRecord.setVictim(victim);
        }

        return crimeRecord;
    }
}
