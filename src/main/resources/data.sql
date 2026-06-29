INSERT INTO doctors (id,name,specialty, phone, email)
VALUES
    ('770b3ce1-ad10-4047-af7c-a8a7ad8aa49b','Dra María Gonzáles', 'Cardiology', '5551001', 'maria.gonzalez@medisalud.com'),
    ('d1259a02-575a-43aa-a9ad-c7d6d760a454','Dr. Carlos Pavon', 'Cardiology', '5562006', 'carlos.pavon@medisalud.com');

--------------------------------

INSERT INTO patients (id,name,identification_number, phone, email)
VALUES
    ('efdb5324-54e5-4b52-a574-5db128e571c9','Louise', '123456789', '5553001', 'lu.valencia@email.com'),
    ('3b7a8be8-67fc-40b3-8ac4-87bc0d5974ae','Christofer', '1152228115', '5553001', 'chris.valencia@email.com'),
    ('fd6d22e9-8046-4fa9-bd21-f302f21542e0','Juan', '987654321', '5554001', 'juan.quintero@email.com');

INSERT INTO patients (id,name,identification_number, phone, email,birth_Day)
VALUES
    ('42663802-5cde-4281-ae31-80b3c54135b0','Salome', '115215512254', '5584001', 'salome.gil@email.com','2060-06-29 08:30:00');

----------------------------------

INSERT INTO appointments (id,doctor_id,patient_id,appointment_date,status,cancellation_date)
VALUES
    ('b6534bf6-051c-434e-aea5-bd20342bafa0','770b3ce1-ad10-4047-af7c-a8a7ad8aa49b','efdb5324-54e5-4b52-a574-5db128e571c9','2026-06-29 08:30:00','PROGRAMADA',NULL),
    ('c230b525-dd3a-40cf-925a-e5ddc68c9f07','770b3ce1-ad10-4047-af7c-a8a7ad8aa49b','efdb5324-54e5-4b52-a574-5db128e571c9','2026-06-30 08:00:00','CANCELADA',NULL),
    ('7cac6cd7-1d98-411b-9df4-df8fa382d605','d1259a02-575a-43aa-a9ad-c7d6d760a454','efdb5324-54e5-4b52-a574-5db128e571c9','2026-06-30 09:00:00','ATENDIDA',NULL),
    ('2c495252-5c5f-456c-a116-696e138a4a73','d1259a02-575a-43aa-a9ad-c7d6d760a454','efdb5324-54e5-4b52-a574-5db128e571c9','2026-06-29 08:30:00','PROGRAMADA',NULL);


---------

INSERT INTO penalties (id,patient_id,created_at)
VALUES
    ('4b6b7fc3-832b-461d-a810-6611d812a045','3b7a8be8-67fc-40b3-8ac4-87bc0d5974ae',CURRENT_TIMESTAMP),
    ('0a01c055-2de3-4589-8095-f42e8d53e678','3b7a8be8-67fc-40b3-8ac4-87bc0d5974ae',CURRENT_TIMESTAMP),
    ('9092ab6d-8faf-4ffa-bd89-6e11786e3657','3b7a8be8-67fc-40b3-8ac4-87bc0d5974ae',CURRENT_TIMESTAMP);