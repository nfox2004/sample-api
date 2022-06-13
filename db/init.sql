CREATE TABLE drugs (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  availability INT NOT NULL DEFAULT 0,
  price FLOAT
);

INSERT INTO drugs (name, availability, price) VALUES
('Vicodin, Norco, Xodol (hydrocodone, acetaminophen)', 100, 14),
('Synthroid, Levoxyl, Unithroid (levothyroxine)', 200, 11),
('Delasone, Sterapred (prednisone)', 150, 5),
('Amoxil (amoxicillin)', 200, 9),
('Neurontin (gabapentin)', 50, 13),
('Prinivil, Zestril (lisinopril)', 60, 7),
('Lipitor (atorvastatin)', 78, 12),
('Glucophage (metformin)', 180, 8),
('Zofran (ondansetron)', 40, 17),
('Motrin (ibuprofen)', 70, 12);