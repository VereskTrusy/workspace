-- vo 만들기
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
where table_name = 'COUNTRIES';

-----------------------------------------------------------------------------------------
--제시된 조건을 만족하는 인사 조회 서비스를 구현하시오.
--대륙별 국가별로 해외 지사 조회.
--해외 지사를 선택한 경우, 해당 지사의 부서 정보 조회
--부서 정보를 선택하면, 해당 부서에 소속된 직원 목록 조회.
--필요시 전체 직원 목록 조회 가능해야함.
--직원 목록 조회시, 부서별 정렬, 급여 내림 차순 정렬 조건 사용.
--직원을 선택하면, 해당 직원의 상세 정보와 인사 기록을 조회할 수 있음.
--상세 정보와 인사 기록 조회시, 부서명과 업무명을 조회할 수 있음.
--선택한 직원에 대해 담당업무나 소속 부서를 변경할 수 있음.
--선택한 직원에 대해 퇴사 처리 가능.
--퇴사자 별도 조회 가능.
-----------------------------------------------------------------------------------------


--제시된 조건을 만족하는 인사 조회 서비스를 구현하시오.
--대륙별 국가별로 해외 지사 조회.
--해외 지사를 선택한 경우, 해당 지사의 부서 정보 조회
-- LOCATION_INFO
SELECT *
FROM REGIONS R
LEFT OUTER JOIN COUNTRIES C
    ON(R.REGION_ID = C.REGION_ID)
LEFT OUTER JOIN LOCATIONS L
    ON(C.COUNTRY_ID = L.COUNTRY_ID)
LEFT OUTER JOIN DEPARTMENTS D
    ON(L.LOCATION_ID = D.LOCATION_ID)
;

SELECT *
FROM REGIONS R
LEFT OUTER JOIN COUNTRIES C
    ON(R.REGION_ID = C.REGION_ID)
;


--필요시 전체 직원 목록 조회 가능해야함.
--직원 목록 조회시, 부서별 정렬, 급여 내림 차순 정렬 조건 사용.
-- EMP_INFO
SELECT *
FROM EMPLOYEES E
WHERE 1=1
--AND E.DEPARTMENT_ID = 10 /* #{} departmentId*/
--AND E.JOB_ID = 'AD_ASST'
ORDER BY 
      DEPARTMENT_ID ASC /* ${} SORT_ODER*/
    , SALARY ASC
;

--직원을 선택하면, 해당 직원의 상세 정보와 인사 기록을 조회할 수 있음.
--상세 정보와 인사 기록 조회시, 부서명과 업무명을 조회할 수 있음.
-- 101 176 200
SELECT *
FROM EMPLOYEES E
INNER JOIN JOB_HISTORY JH
    ON(E.EMPLOYEE_ID = JH.EMPLOYEE_ID)
INNER JOIN JOBS J
    ON(JH.JOB_ID = J.JOB_ID)
INNER JOIN DEPARTMENTS D
    ON(JH.DEPARTMENT_ID = D.DEPARTMENT_ID)
WHERE 1=1
AND E.EMPLOYEE_ID = 176
;



-----------------------------------------------------------------------------------------

-- 파일명

-----------------------------------------------------------------------------------------
countries
departments
employees
jobs
job_history
locations
regions
retire






-----------------------------------------------------------------------------------------

-- vo 만들기

-----------------------------------------------------------------------------------------
-- retire
SELECT 'private '
|| CASE
    WHEN COL.DATA_TYPE = 'DATE' THEN 'String '
    WHEN COL.DATA_TYPE = 'NUMBER' THEN 'Long '
    ELSE 'String '
    END
|| SUBSTR(LOWER(COL.COLUMN_NAME), 1, 1) || SUBSTR(REPLACE(INITCAP(COL.COLUMN_NAME), '_', ''), 2)
|| '; // ' 
|| COM.COMMENTS || ', '
|| 'type:' || COL.DATA_TYPE || ', '
|| 'nullable:' || COL.NULLABLE
FROM COLS COL
INNER JOIN USER_COL_COMMENTS COM
    ON (COL.TABLE_NAME = COM.TABLE_NAME AND COL.COLUMN_NAME = COM.COLUMN_NAME)
WHERE COL.TABLE_NAME = 'RETIRE';

-------------------------------------------------------------------------------------------------------------------------

-- INSERT VALUES 만들기

-------------------------------------------------------------------------------------------------------------------------
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
AND COLS.TABLE_NAME = 'PROD'
;

-------------------------------------------------------------------------------------------------------------------------

-- [함수] 컬럼명 스네이크 > 카멜 표기법으로 변환

-------------------------------------------------------------------------------------------------------------------------
create or replace FUNCTION FN_SNAKETOCAMEL(
    COLUMN_NAME VARCHAR2 
)
RETURN VARCHAR2
IS
    V_TMP VARCHAR2(50);
BEGIN
    V_TMP := SUBSTR(LOWER(COLUMN_NAME), 1, 1) 
        || SUBSTR(REPLACE(INITCAP(COLUMN_NAME), '_', ''), 2);

    RETURN V_TMP;
END;



-------------------------------------------------------------------------------------------------------------------------

-- 코멘트 입력 쿼리

-------------------------------------------------------------------------------------------------------------------------
-- COMMENT ON COLUMN PROD.PROD_ID IS '상품코드';
SELECT 
    'COMMENT ON COLUMN '
    || TABLE_NAME || '.' || COLUMN_NAME
    || ' IS ''''; '
FROM COLS
WHERE TABLE_NAME = 'REGIONS';



-------------------------------------------------------------------------------------------------------------------------

-- UPDATE SET 절 만들기

-------------------------------------------------------------------------------------------------------------------------
SELECT 
', ' || 
COLUMN_NAME || ' = #{' || FN_SNAKETOCAMEL(COLUMN_NAME) 
|| ', ' 
|| case
    when DATA_TYPE = 'DATE' then 'jdbcType=DATE'
    when DATA_TYPE = 'NUMBER' then 'jdbcType=NUMERIC'
    else 'jdbcType=VARCHAR '
    end
|| '}'
FROM COLS
WHERE COLS.TABLE_NAME = 'EMPLOYEES'
;



-------------------------------------------------------------------------------------------------------------------------

-- 폼 UI 생성

-------------------------------------------------------------------------------------------------------------------------
SELECT 
'<tr><th>' || UCC.COMMENTS || '</th><td><input type="'
|| CASE
   WHEN COL.DATA_TYPE IN ('DATE', 'NUMBER') THEN LOWER(DATA_TYPE)
   WHEN COL.DATA_TYPE = 'TIMESTAMP' THEN 'datetime-local'
   ELSE 'text'
   END
|| '" name="' || FN_SNAKETOCAMEL(COL.COLUMN_NAME) || '" '
|| DECODE(COL.NULLABLE, 'N', 'required="required"', '')
|| ' value="${'
|| FN_SNAKETOCAMEL(COL.TABLE_NAME)
|| '.'
|| FN_SNAKETOCAMEL(COL.COLUMN_NAME)
|| '}" class="form-control" />'
|| '<span class="text-danger">${errors.'
|| FN_SNAKETOCAMEL(COL.COLUMN_NAME)
|| '}</span>'
|| '</td></tr>'
FROM COLS COL
INNER JOIN USER_COL_COMMENTS UCC 
    ON (COL.TABLE_NAME = UCC.TABLE_NAME AND COL.COLUMN_NAME = UCC.COLUMN_NAME)
WHERE 1=1
AND COL.TABLE_NAME = 'EMPLOYEES';

