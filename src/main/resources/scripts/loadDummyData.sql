-- Prompt dummy data
INSERT INTO prompt (description, promptvalue) VALUES ('Test prompt1', 'Describe the result of the report data overall as whole');
INSERT INTO prompt (description, promptvalue) VALUES ('Test prompt2', 'Describe the result of the report data briefly');
INSERT INTO prompt (description, promptvalue) VALUES ('Test prompt3', 'Describe the result of the report data only by numbers');

-- Report data dummy
INSERT INTO reportdata (reporttime, reportname) VALUES (DATE '2025-06-15', 'Blood Report Test 1');