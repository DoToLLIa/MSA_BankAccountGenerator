FROM maven:3.6.3-jdk-8
ARG PROJECT_VERSION=0.1
RUN mkdir /MSA_BankAccountGenerator
COPY . /MSA_BankAccountGenerator
RUN cd /MSA_BankAccountGenerator && \
    mvn clean package &&  \
    mv /MSA_BankAccountGenerator/target/BankAccountGenerator-${PROJECT_VERSION}.jar /BankAccountGenerator.jar && \
    rm -r /MSA_BankAccountGenerator
EXPOSE 8080
CMD ["java", "-jar", "/BankAccountGenerator.jar"]