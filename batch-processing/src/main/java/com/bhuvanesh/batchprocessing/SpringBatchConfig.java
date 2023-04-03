package com.bhuvanesh.batchprocessing;

import java.util.Hashtable;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.core.configuration.annotation.job;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

	private JobRegistry jobBuilderFactory;
//	private StepBuilderFactory stepBuilderFactory;
	private CustomerRepository customerRepository;
	private PlatformTransactionManager transactionManager;
	private JobRepository jobRepository;
	
	//ItemProcessor
	@Bean
	public CustomerProcessor processor() {
		
	return  new CustomerProcessor();
	} 
	
	//ItemWriter
	@Bean
	public RepositoryItemWriter<Customer> writer()
	{
		RepositoryItemWriter<Customer> itemWriter = new RepositoryItemWriter<>();
		itemWriter.setRepository(customerRepository);
		itemWriter.setMethodName("save");
		return itemWriter;
	}
	
	//ItemReader
	@Bean
	public FlatFileItemReader<Customer> reader(){
		FlatFileItemReader<Customer> itemReader = new FlatFileItemReader<Customer>();
		
		itemReader.setResource(new FileSystemResource("src/main/resources/SampleCSVFile_119kb.csv"));
		itemReader.setName("csvReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
	
		return itemReader;
	}

	private LineMapper<Customer> lineMapper() {
		// TODO Auto-generated method stub
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","firstName","lastName","quantity","expenses","income","bonus","district","orderList","rateOfInterest");
		
		BeanWrapperFieldSetMapper<Customer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Customer.class);
		
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		System.out.println("Linemapper is executing");
		return lineMapper;
		
	}
	
	//step
	@Bean
	public Step step1() {
		System.out.println("step1 is executing");
		return new StepBuilder("csv-step",jobRepository)
		        .<Customer, Customer>chunk(10, transactionManager)
		        .reader(reader())
		        .processor(processor())
		        .writer(writer())
//		        .taskExecutor(taskExecutor())
		        .build();
//		return stepBuilderFactory.get("csv-step").<Customer,Customer>chunk(10)
//				.reader(reader())
//				.processor(processor())
//				.writer(writer())
//				.build();
		
	}
	
	//Job
	@Bean
	public Job runJob() {
//		jobBuilderFactory.ge
		   return new JobBuilder("myJob",jobRepository)
		            .flow(step1())
		            .end()
		            .build();
//		return jobBuilderFactory.getJob("importCustomers")
//				.flow(step1()).end().build();
	}
}
