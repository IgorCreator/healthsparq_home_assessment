# To run application with docker please 1) Install docker on your local system
# 2) execute docker build command. Example: "docker build -t  demo_assessment_with_args.jar ."
# 3) execute docker run command for built image. Example: "docker run -p 8080:8080 demo_assessment_with_args.jar"

FROM openjdk:8
ADD target/demo_assessment.jar demo_assessment.jar
ADD additional_resources/input.csv input.csv
ENTRYPOINT ["java", "-jar","demo_assessment.jar", "-inputFile", "./input.csv", "-sortParam", "Name", "-outputDir", ".", "-solutionVer", "1"]
EXPOSE 8080