INSERT INTO EMPLOYEE (VISA, FIRST_NAME, LAST_NAME, BIRTH_DATE)
VALUES
    ('LT', 'LE','TAN','1998-02-02'),
    ('LL', 'LOC', 'LAM', '1999-01-01'),
    ('VV', 'VO VAN', 'LAM', '1999-01-05'),
    ('VL', 'VON', 'LAN', '1999-01-09'),
    ('PP', 'PHUC PHAT', 'LAM', '1997-01-12'),
    ('ABC', 'ANH BAN', 'LAM', '1999-01-05'),
    ('VVL', 'VO VAN LAM', 'LAN', '1999-01-09'),
    ('YPY', 'YOU PILOT YOU', 'LAM', '1997-01-12');
INSERT INTO "group" (GROUP_LEADER_ID)
VALUES
    (1),
    (2);
INSERT INTO PROJECT (NAME, GROUP_ID, PROJECT_NUMBER, CUSTOMER, START_DATE, END_DATE, STATUS)
VALUES
    ('EFV', 1, 12, 'TAM LE LOC', '2020-04-14','2020-04-28', 'NEW'),
    ('CXTRANET', 2, 15, 'VO VAN LAM', '2018-01-16','2020-04-14','FIN'),
    ('CRYSTAL BALL', 1, 14, 'CONG PHUONG', '2018-01-28','2020-04-14','FIN'),
    ('IOC CLIENT EXTRANET', 1, 10, 'CONG CONG', '2019-09-14','2020-04-14','PLA'),
    ('TESTIFY FOR MURDERED', 1, 9, 'CONG CONG', '2020-09-14','2020-04-25','PLA'),
    ('CLIENT ET', 1, 1, 'CONG CONG', '2020-01-14','2020-04-20','PLA'),
    ('INTERNET ', 2, 2, 'CONG CONG', '2020-01-14','2020-04-25','PLA'),
    ('IRON MAN', 1, 4, 'CONG CONG', '2020-01-14','2020-04-14','NEW'),
    ('SPIDER MAN', 1, 3, 'CONG CONG', '2020-01-14','2020-04-14','FIN'),
    ('BLACK WIDOWS', 1, 5, 'CONG CONG', '2020-02-14','2020-04-14','NEW'),
    ('DOCTOR STRANGE', 1, 6, 'CONG CONG', '2020-03-14','2020-04-14','NEW'),
    ('ABILITY', 1, 7, 'CONG CONG', '2020-02-14','2020-04-14','PLA'),
    ('GUILTY', 2, 8, 'CONG CONG', '2020-02-14','2020-04-14','INP'),
    ('KSTA MIGRATION', 1, 20, 'NO REPLY', '2019-08-31','2020-04-14','INP');
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEE_ID, PROJECT_ID)
VALUES
    (4,1),
    (5,1),
    (6,1),
    (4,2),
    (5,2),
    (6,2),
    (4,4),
    (5,4),
    (6,4),
    (6,5),
    (4,5),
    (5,4),
    (6,5),
    (1,4);