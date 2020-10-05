package com.invenio.obs.configuration;

	import javax.sql.DataSource;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.batch.core.Job;
	import org.springframework.batch.core.Step;
	import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
	import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
	import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
	import org.springframework.batch.core.launch.support.RunIdIncrementer;
	import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
	import org.springframework.batch.item.database.JdbcBatchItemWriter;
	import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
	import org.springframework.batch.item.file.FlatFileItemReader;
	import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
	import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.core.io.ClassPathResource;

import com.invenio.obs.beans.Customer;
import com.invenio.obs.listener.JobCompletionNotificationListener;
import com.invenio.obs.processor.CustomerProcessor;

	@Configuration
	@EnableBatchProcessing
	public class BatchConfiguration {

		private Logger log=LoggerFactory.getLogger(BatchConfiguration.class);
	  @Autowired
	  public JobBuilderFactory jobBuilderFactory;

	  @Autowired
	  public StepBuilderFactory stepBuilderFactory;

	  @Bean
	  public FlatFileItemReader<Customer> reader() {
		  log.info("Reading the csv file..");
	    return new FlatFileItemReaderBuilder<Customer>()
	      .name("customerItemReader")
	      .resource(new ClassPathResource("First3CustomerData.csv"))
	      .delimited()
	      .names(new String[]{"customerAccountNumber", "customerId", "customerPin", "customerFirstName", "customerMiddleName"," customerLastName", "customerUserName", "customerEmailId", "customerDob", "customerAddress", "customerPassword", "customerPhoneNumber", "customerAdhaarNumber", "customerPanNumber", "customerAccountType", "customerOwnership", "customerBranch", "customerIfscCode", "customerBalance"})
	      .fieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
	        setTargetType(Customer.class);
	      }})
	      .build();
	  }

	  @Bean
	  public CustomerProcessor processor() {
	    return new CustomerProcessor();
	  }

	  @Bean
	  public JdbcBatchItemWriter<Customer> writer(DataSource dataSource) {
		  log.info("writing data into db..");
	    return new JdbcBatchItemWriterBuilder<Customer>()
	      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	      .sql("INSERT INTO customer (customerAccountNumber, customerId, customerPin, customerFirstName,customerMiddleName, customerLastName, customerUserName, customerEmailId, customerDob,customerAddress, customerPassword, customerPhoneNumber, customerAdhaarNumber,customerPanNumber, customerAccountType, customerOwnership, customerBranch,customerIfscCode, customerBalance)VALUES (:customerAccountNumber, :customerId, :customerPin,:customerFirstName, :customerMiddleName, :customerLastName,:customerUserName, :customerEmailId, :customerDob,:customerAddress, :customerPassword, :customerPhoneNumber,:customerAdhaarNumber, :customerPanNumber, :customerAccountType,:customerOwnership, :customerBranch, :customerIfscCode, :customerBalance)")
	      .dataSource(dataSource)
	      .build();
	  }

	  @Bean
	  public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
		  log.info("here");
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	  }

	  @Bean
	  public Step step1(JdbcBatchItemWriter<Customer> writer) {
		  
		  log.info("Step Builder");
	    return stepBuilderFactory.get("step1")
	      .<Customer, Customer> chunk(1)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer)
	      .build();
	  }
	}
	
	

