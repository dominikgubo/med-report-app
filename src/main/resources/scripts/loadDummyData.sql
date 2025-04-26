-- TODO; Potentially change dummy data approach or refactor at least

-- Prompt dummy data
INSERT INTO prompt (description, promptvalue) VALUES ('Test prompt1', 'Describe the result of the report data overall as whole');
INSERT INTO prompt (description, promptvalue) VALUES ('Test prompt2', 'Describe the result of the report data briefly');
INSERT INTO prompt (description, promptvalue) VALUES ('Test prompt3', 'Describe the result of the report data only by numbers');

-- Patient dummy data
ALTER TABLE patient ALTER COLUMN weight DROP NOT NULL;
INSERT INTO patient(firstName, lastName, bloodType, sex, age) VALUES ('John', 'Doe', 'A+', 'M', 45);
INSERT INTO patient(firstName, lastName, bloodType, sex, age) VALUES ('Slavko', 'Kockica', 'B+', 'M', 22);

-- Report data dummy
INSERT INTO report (patient, reportType) values (1, 'Blood');

-- Medical Parameter dummy data
INSERT INTO medical_parameter(parameterName, report, parameterValue, referenceValueRange) VALUES ('Hemoglobin', 1, '132 g/L', '135 – 175 g/L (men), 120 – 160 g/L (women)');
INSERT INTO medical_parameter(parameterName, report, parameterValue, referenceValueRange) VALUES ('White Blood Cells', 1, '7.2 x10⁹/L', '4.0 – 10.0 x10⁹/L');
INSERT INTO medical_parameter(parameterName, report, parameterValue, referenceValueRange) VALUES ('Hematocrit', 1, '0.41 L/L', ' 0.40 – 0.50 L/L (men), 0.35 – 0.47 L/L (women)');
INSERT INTO medical_parameter(parameterName, report, parameterValue) VALUES ('Red Cell Distribution Width (RDW)', 1, '15.5%');