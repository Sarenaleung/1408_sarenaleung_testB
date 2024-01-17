package com.example.Q5.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.repository.JobRepository;

import com.example.Q5.model.CrimeRecord;
import com.example.Q5.repository.AreaRepository;
import com.example.Q5.repository.CrimeCodeRepository;
import com.example.Q5.repository.PremisCodeRepository;
import com.example.Q5.repository.StatusRepository;
import com.example.Q5.repository.VictimRepository;
import com.example.Q5.repository.WeaponRepository;

import jakarta.persistence.EntityManagerFactory;

@Configuration
// @EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<CSVRecordDTO> reader() {
        String inputFile = "Q5/Q5/src/main/resources/static/la_crime_2010_to_2023.csv";

        return new FlatFileItemReaderBuilder<CSVRecordDTO>()
                .name("crimeRecordItemReader")
                .linesToSkip(1)
                .resource(new FileSystemResource(inputFile))
                .delimited()
                .names(new String[] {
                        "DR_NO",
                        "Date Rptd",
                        "DATE OCC",
                        "TIME OCC",
                        "AREA",
                        "AREA NAME",
                        "Rpt Dist No",
                        "Part 1-2",
                        "Crm Cd",
                        "Crm Cd Desc",
                        "Mocodes",
                        "Vict Age",
                        "Vict Sex",
                        "Vict Descent",
                        "Premis Cd",
                        "Premis Desc",
                        "Weapon Used Cd",
                        "Weapon Desc",
                        "Status",
                        "Status Desc",
                        "Crm Cd 1",
                        "Crm Cd 2",
                        "Crm Cd 3",
                        "Crm Cd 4",
                        "LOCATION",
                        "Cross Street",
                        "LAT",
                        "LON",
                        "AREA" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<CSVRecordDTO>() {
                    {
                        setTargetType(CSVRecordDTO.class);
                    }
                })
                .build();
    }

    @Bean
    public ItemProcessor<CSVRecordDTO, CrimeRecord> crimeRecordProcessor(
            CrimeCodeRepository crimeCodeRepository,
            AreaRepository areaRepository,
            PremisCodeRepository premisCodeRepository,
            StatusRepository statusRepository,
            WeaponRepository weaponRepository,
            VictimRepository victimRepository
    ) {
        return new CrimeRecordProcessor(crimeCodeRepository, areaRepository, premisCodeRepository, statusRepository, weaponRepository, victimRepository);
    }



    @Bean
    public JpaItemWriter<CrimeRecord> writer(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<CrimeRecord>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }

    @Bean
    public Step step(
            ItemProcessor<CSVRecordDTO, CrimeRecord> crimItemProcessor,
            FlatFileItemReader<CSVRecordDTO> reader,
            JpaItemWriter<CrimeRecord> writer, JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<CSVRecordDTO, CrimeRecord>chunk(10, transactionManager)
                .reader(reader)
                .processor(crimItemProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importCrimeJob(Step step, JobRepository jobRepository) {
        return new JobBuilder("importCrimeJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }
}
