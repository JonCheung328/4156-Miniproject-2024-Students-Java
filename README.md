# Welcome Students of 4156

Please follow the assignment specifications on Courseworks when completing this project.

## Part I:
To perform stylecheck, please run the following command:
```bash
cd IndividualProject
mvn checkstyle:check
```

## Part II:
To execute the tests and attain the test report, please run the following command:
```bash
cd IndividualProject
mvn clean test jacoco:report 
```
The report will be available at IndividualProject/target/site/jacoco/index.html

## Part III:
I have selected PMD as the static bug finder. To perform bug checking, please run the following command
```bash
alias pmd="pmd-bin-7.5.0/bin/pmd" 
pmd check -d IndividualProject -R rulesets/java/quickstart.xml -r pmd.txt
```
The bugs found will be available at pmd.txt