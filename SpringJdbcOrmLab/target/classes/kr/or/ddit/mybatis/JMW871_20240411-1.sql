-------------------------------------------------------------------------------------------------------------------------

-- 2024.04.11

-------------------------------------------------------------------------------------------------------------------------

-- 테이블 추가
CREATE TABLE PERSON(
    ID VARCHAR2(5) PRIMARY KEY,
    NAME VARCHAR2(20 CHAR) NOT NULL,
    GENDER CHAR(1) NOT NULL,
    AGE NUMBER(3) NOT NULL,
    ADDRESS VARCHAR2(100 CHAR)
);
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('M0014','Oliver','M',42,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('M0013','Sebastian','M',2,'Suite 582 37949 Schuppe Lodge, West Christinemouth, OR 43952-4582');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('M0012','William','M',2,'22835 Clora Point, East Erlene, VA 60131');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('M0011','Arvid','F',33,'Suite 697 17465 Deshawn Street, Ulrikeville, WY 45445');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('R0014','Apollo','F',17,'564 Koss Spur, New Jacobberg, VT 00325-5882');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('R0013','Henry','F',47,'Apt. 317 280 Kali Springs, New Rickyburgh, IA 30146');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('R0012','Jackson','F',33,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('B0014','Fernando','F',31,'Suite 582 37949 Schuppe Lodge, West Christinemouth, OR 43952-4582');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('R0011','Arnold','M',42,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('B0013','Walter','F',19,'Suite 150 88202 Williamson Heights, Swiftmouth, LA 66889-7826');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('B0012','Damian','M',16,'9085 Berge Dale, Treutelburgh, NJ 04656-2080');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('B0011','Zephyrus','F',49,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('W0014','Jeffery','M',10,'Apt. 317 280 Kali Springs, New Rickyburgh, IA 30146');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('W0013','Luke','F',19,'Suite 521 76775 Heaney Club, Sengertown, VA 53681-9918');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('W0012','Icarus','F',15,'745 Hilpert Ports, Lake Ervinberg, MS 86173');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('W0011','Charles','M',36,'7159 Roxann Prairie, West Keira, MN 20894-9910');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('G0014','Adolph','F',41,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('G0013','Ozzy','F',7,'Apt. 987 1707 Tillman Meadows, Genniefort, VA 84799');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('G0012','Will','F',45,'Suite 167 71126 Farrell Row, Port Shakitahaven, NM 85607-6079');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('G0011','Wade','M',48,'Suite 697 17465 Deshawn Street, Ulrikeville, WY 45445');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('L0014','Kai','M',32,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('L0013','David','F',7,'Suite 826 3282 Eduardo River, South Florentina, ME 02585');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('L0012','Benjamin','M',2,'Apt. 317 280 Kali Springs, New Rickyburgh, IA 30146');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('L0011','Theron','F',19,'299 Vandervort Street, East Genaro, ME 64219');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Q0014','Phineas','F',27,'Suite 167 71126 Farrell Row, Port Shakitahaven, NM 85607-6079');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Q0013','Benjamin','M',2,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Q0012','Joseph','M',12,'Suite 150 88202 Williamson Heights, Swiftmouth, LA 66889-7826');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('A0014','Ethan','F',49,'3222 Jacob Radial, North Bradleyville, NE 40606');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Q0011','Parker','F',7,'6362 Wisozk Squares, Magdaburgh, IN 30755');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('A0013','Jack','M',48,'9085 Berge Dale, Treutelburgh, NJ 04656-2080');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('A0012','Gunner','M',18,'Suite 521 76775 Heaney Club, Sengertown, VA 53681-9918');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('A0011','Jeffery','F',5,'22835 Clora Point, East Erlene, VA 60131');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('V0014','Luke','F',41,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('V0013','Luciano','F',39,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('V0012','Lysander','M',26,'Suite 521 76775 Heaney Club, Sengertown, VA 53681-9918');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('V0011','William','M',10,'Apt. 706 636 Windler Turnpike, Lake Lazaro, MN 28795-6462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('F0014','Liam','M',22,'299 Vandervort Street, East Genaro, ME 64219');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('F0013','Caius','M',2,'Suite 150 88202 Williamson Heights, Swiftmouth, LA 66889-7826');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('F0012','Edgar','F',9,'Suite 521 76775 Heaney Club, Sengertown, VA 53681-9918');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('F0011','Richard','M',6,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('K0014','Harrison','M',12,'Suite 582 37949 Schuppe Lodge, West Christinemouth, OR 43952-4582');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('K0013','Gabriel','M',32,'6362 Wisozk Squares, Magdaburgh, IN 30755');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('K0012','Joseph','F',9,'Apt. 706 636 Windler Turnpike, Lake Lazaro, MN 28795-6462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('K0011','Lucas','F',23,'Apt. 987 1707 Tillman Meadows, Genniefort, VA 84799');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('P0014','James','F',45,'Suite 521 76775 Heaney Club, Sengertown, VA 53681-9918');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('P0013','Dashiell','F',41,'564 Koss Spur, New Jacobberg, VT 00325-5882');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('P0012','Oberon','F',21,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('P0011','Liam','F',23,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('U0014','Raphael','F',5,'Suite 521 76775 Heaney Club, Sengertown, VA 53681-9918');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('U0013','Daniel','F',15,'Apt. 706 636 Windler Turnpike, Lake Lazaro, MN 28795-6462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('U0012','Ian','M',38,'Suite 150 88202 Williamson Heights, Swiftmouth, LA 66889-7826');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('U0011','Brandon','M',24,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('E0014','Joseph','M',2,'76713 Durgan Isle, Port Damonport, AR 91269-7522');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('E0013','David','F',31,'Suite 956 88626 Mueller Parkways, Hermannfort, CT 05452');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('E0012','Adonis','M',26,'76713 Durgan Isle, Port Damonport, AR 91269-7522');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('E0011','Oliver','F',15,'6779 Gene Stravenue, East Annamariemouth, SC 25437-8540');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Z0014','Zephaniah','F',43,'745 Hilpert Ports, Lake Ervinberg, MS 86173');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Z0013','Daniel','M',12,'564 Koss Spur, New Jacobberg, VT 00325-5882');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Z0012','Finn','F',11,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Z0011','James','M',34,'3222 Jacob Radial, North Bradleyville, NE 40606');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('J0014','Ambrose','M',22,'Apt. 317 280 Kali Springs, New Rickyburgh, IA 30146');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('J0013','Garrett','M',28,'Apt. 706 636 Windler Turnpike, Lake Lazaro, MN 28795-6462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('J0012','Jeffery','M',44,'Suite 167 71126 Farrell Row, Port Shakitahaven, NM 85607-6079');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('J0011','Ignatius','F',1,'Suite 167 71126 Farrell Row, Port Shakitahaven, NM 85607-6079');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('O0014','Oliver','M',14,'Suite 150 88202 Williamson Heights, Swiftmouth, LA 66889-7826');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('O0013','Samuel','F',7,'Apt. 987 1707 Tillman Meadows, Genniefort, VA 84799');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('O0012','Adelio','F',33,'745 Hilpert Ports, Lake Ervinberg, MS 86173');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('O0011','Lucas','M',46,'3222 Jacob Radial, North Bradleyville, NE 40606');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('T0014','Alistair','M',30,'3222 Jacob Radial, North Bradleyville, NE 40606');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('T0013','Michael','M',32,'Apt. 706 636 Windler Turnpike, Lake Lazaro, MN 28795-6462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('T0012','Miles','F',27,'Suite 826 3282 Eduardo River, South Florentina, ME 02585');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('T0011','Zephyr','M',36,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('D0014','Thaddeus','F',41,'Suite 826 3282 Eduardo River, South Florentina, ME 02585');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('D0013','Cormac','M',48,'3222 Jacob Radial, North Bradleyville, NE 40606');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('D0012','Orion','M',14,'3222 Jacob Radial, North Bradleyville, NE 40606');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('D0011','Carter','F',13,'564 Koss Spur, New Jacobberg, VT 00325-5882');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Y0014','Alistair','F',23,'564 Koss Spur, New Jacobberg, VT 00325-5882');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Y0013','Liam','F',21,'Suite 582 37949 Schuppe Lodge, West Christinemouth, OR 43952-4582');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Y0012','Nathan','M',30,'Suite 956 88626 Mueller Parkways, Hermannfort, CT 05452');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('Y0011','Kaelan','M',10,'Suite 582 37949 Schuppe Lodge, West Christinemouth, OR 43952-4582');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('I0014','Adonis','F',49,'564 Koss Spur, New Jacobberg, VT 00325-5882');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('I0013','Leonardo','M',10,'299 Vandervort Street, East Genaro, ME 64219');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('I0012','Ignatius','M',26,'Apt. 234 3695 Parisian Ways, Port Jeffmouth, AR 73855-1769');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('I0011','Henry','M',40,'76713 Durgan Isle, Port Damonport, AR 91269-7522');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('N0014','Theodore','F',39,'22835 Clora Point, East Erlene, VA 60131');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('N0013','Henry','M',30,'22835 Clora Point, East Erlene, VA 60131');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('N0012','Antonio','F',9,'22835 Clora Point, East Erlene, VA 60131');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('N0011','Kaelan','M',16,'Suite 150 88202 Williamson Heights, Swiftmouth, LA 66889-7826');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('S0014','Soren','F',25,'22835 Clora Point, East Erlene, VA 60131');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('S0013','Mason','F',37,'Suite 956 88626 Mueller Parkways, Hermannfort, CT 05452');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('S0012','Bruno','F',29,'7159 Roxann Prairie, West Keira, MN 20894-9910');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('S0011','Gael','M',38,'Suite 582 37949 Schuppe Lodge, West Christinemouth, OR 43952-4582');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('C0014','Carter','M',38,'6362 Wisozk Squares, Magdaburgh, IN 30755');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('C0013','Edith','F',5,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('C0012','Diallo','F',25,'6362 Wisozk Squares, Magdaburgh, IN 30755');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('C0011','Nathan','M',0,'7159 Roxann Prairie, West Keira, MN 20894-9910');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('X0014','Dashiell','F',11,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('X0013','Lucas','M',18,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('X0012','Santiago','M',16,'Apt. 706 636 Windler Turnpike, Lake Lazaro, MN 28795-6462');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('X0011','Jackson','M',32,'Suite 697 17465 Deshawn Street, Ulrikeville, WY 45445');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('H0014','Alexander','F',49,'76713 Durgan Isle, Port Damonport, AR 91269-7522');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('H0013','Danial','F',13,'Suite 697 17465 Deshawn Street, Ulrikeville, WY 45445');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('H0012','Lysander','F',25,'Apt. 317 280 Kali Springs, New Rickyburgh, IA 30146');
Insert into PERSON (ID,NAME,GENDER,AGE,ADDRESS) values ('H0011','James','F',21,'Suite 940 4436 Kathaleen View, North Salliestad, NJ 00724-5462');
COMMIT;




-- 테이블 추가
DROP TABLE ADDRESSBOOK CASCADE CONSTRAINTS;
CREATE TABLE ADDRESSBOOK(
     ADRS_NO NUMBER(4), -- 주소록 번호
     MEM_ID VARCHAR2(20), -- 주소록의 소유자
     ADRS_NAME VARCHAR2(30), --  이름
     ADRS_TEL VARCHAR2(20), -- 휴대폰
     ADRS_ADD VARCHAR2(100), -- 주소
     CONSTRAINT PK_ADRS PRIMARY KEY (ADRS_NO), --- 기본키 제약조건
     CONSTRAINT FK_ADRS_MEMBER FOREIGN KEY (MEM_ID) REFERENCES MEMBER(MEM_ID) -- 참조키 제약조건
);

-- PERSON vo 만들기
-- 테이블 컬럼 > java 코드로
select 'private '
|| case
    when DATA_TYPE = 'DATE' then 'String '
    when DATA_TYPE = 'NUMBER' then 'Long '
    else 'String '
    end
|| SUBSTR(LOWER(COLUMN_NAME), 1, 1) || SUBSTR(REPLACE(INITCAP(COLUMN_NAME), '_', ''), 2)
|| ';'
from cols
where table_name = 'PERSON';


-- Person 테이블 리스트랑 단건조회
SELECT
    ID
    ,NAME
    ,GENDER
    ,AGE
    ,ADDRESS
FROM
    PERSON;

SELECT 
     ID
    ,NAME
    ,GENDER
    ,AGE
    ,ADDRESS
FROM PERSON
WHERE ID = #{id};

-- ADDRESSBOOK
-- 테이블 컬럼 > java 코드로
select 'private '
|| case
    when DATA_TYPE = 'DATE' then 'String '
    when DATA_TYPE = 'NUMBER' then 'Long '
    else 'String '
    end
|| SUBSTR(LOWER(COLUMN_NAME), 1, 1) || SUBSTR(REPLACE(INITCAP(COLUMN_NAME), '_', ''), 2)
|| ';'
from cols
where table_name = 'ADDRESSBOOK';


-- 목록조회
SELECT
    ADRS_NO
    ,MEM_ID
    ,ADRS_NAME
    ,ADRS_TEL
    ,ADRS_ADD
FROM
    ADDRESSBOOK;


-- 데이터 삽입
SELECT 
    NVL(MAX(ADRS_NO), 0) + 1 AS ADRS_NO
FROM ADDRESSBOOK
;

-- INSERT VALUES 만들기
SELECT 
', #{' || FN_SNAKETOCAMEL(COLUMN_NAME) 
|| ', ' 
|| case
    when DATA_TYPE = 'DATE' then 'jdbcType=DATE'
    when DATA_TYPE = 'NUMBER' then 'jdbcType=NUMERIC'
    else 'jdbcType=VARCHAR '
    end
|| '}'
FROM COLS
WHERE 1=1
AND COLS.TABLE_NAME = 'ADDRESSBOOK'
;

INSERT INTO ADDRESSBOOK (
     ADRS_NO
    ,MEM_ID
    ,ADRS_NAME
    ,ADRS_TEL
    ,ADRS_ADD
) VALUES (
      #{adrsNo, jdbcType=NUMERIC}
    , #{memId, jdbcType=VARCHAR }
    , #{adrsName, jdbcType=VARCHAR }
    , #{adrsTel, jdbcType=VARCHAR }
    , #{adrsAdd, jdbcType=VARCHAR }
);











