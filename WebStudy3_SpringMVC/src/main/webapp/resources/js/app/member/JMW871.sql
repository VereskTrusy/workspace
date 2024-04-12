
---------------------------------------------------------

2024.03.26

---------------------------------------------------------
UPDATE MEMBER
SET
      MEM_PASS = ? 
    , MEM_NAME = ? 
    , MEM_REGNO1 = ? 
    , MEM_REGNO2 = ? 
    , MEM_BIR = ? 
    , MEM_ZIP = ? 
    , MEM_ADD1 = ? 
    , MEM_ADD2 = ? 
    , MEM_HOMETEL = ? 
    , MEM_COMTEL = ? 
    , MEM_HP = ? 
    , MEM_MAIL = ? 
    , MEM_JOB = ? 
    , MEM_LIKE = ? 
    , MEM_MEMORIAL = ? 
    , MEM_MEMORIALDAY = ? 
    , MEM_MILEAGE = ? 
    , MEM_DELETE = ? 
WHERE MEM_ID = ?;

UPDATE MEMBER
SET
    MEM_DELETE = 'Y'
WHERE MEM_ID = ?;


SELECT *
FROM USER_TAB_COLUMNS
WHERE TABLE_NAME = 'MEMBER';

-- MEMBER 테이블에 해당하는 코멘트 가져오기
SELECT '<th>' || COMMENTS || '</th>'
FROM USER_COL_COMMENTS
WHERE TABLE_NAME = 'MEMBER';

-- 함수 만들기
CREATE OR REPLACE FUNCTION FN_SNAKETOCAMEL(
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



-- <tr><th>회원번호</th><td id="memId"></td></tr>
-- FN_SNAKETOCAMEL(COLUMN_NAME)
SELECT '<tr><th>'||COMMENTS
    ||'</th><td id="'
    ||FN_SNAKETOCAMEL(COLUMN_NAME)
    ||'"></td></tr>'
FROM USER_COL_COMMENTS
WHERE TABLE_NAME = 'MEMBER';




select mem_pass
from member;
-----------------------------------------------------------------------------------------------------------------------------

-- HTML 코드 만들기
/*
cols 테이블에서 조회
TABLE_NAME
COLUMN_NAME
NULLABLE

USER_COL_COMMENTS 테이블에서 조회
COMMENTS

<tr><th>회원아이디</th><td><input type="text" name="memId" required="required" class="form-control" value="${memId}" /><span class="error"></span>${errors.memId}</td></tr>
*/
-- 컬럼 데이터 조회
select *
from cols COL
where TABLE_NAME = 'MEMBER';
-- 코멘트 조회
SELECT *
FROM USER_COL_COMMENTS UCC
WHERE TABLE_NAME = 'MEMBER';

-- 동작쿼리
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
AND COL.TABLE_NAME = 'MEMBER';

-- 네츄럴 조인 사용해보기
SELECT *
FROM COLS NATURAL JOIN USER_COL_COMMENTS
WHERE 1=1
AND TABLE_NAME = 'MEMBER';
-----------------------------------------------------------------------------------------------------------------------------

-- 파라미터 만들기
select
    case
    when DATA_TYPE = 'DATE' then 'String '
    when DATA_TYPE = 'NUMBER' then 'String '
    else 'String '
    end
|| substr(lower(column_name), 1, 1)
|| substr(replace(initcap(column_name), '_', ''), 2)
|| ' = '
||  case
    when DATA_TYPE = 'DATE' then '(String) '
    when DATA_TYPE = 'NUMBER' then '(String) '
    else '(String) '
    end
|| 'req.getParameter("'
|| substr(lower(column_name), 1, 1)
|| substr(replace(initcap(column_name), '_', ''), 2)
|| '");'
from cols
where table_name = 'MEMBER';

-----------------------------------------------------------------------------------------------------------------------------
-- 필수 파라미터 코드조각
--if(StringUtils.isBlank(member.getMemId()) ) {
--    valid = false;
--    errors.put("memId", "아이디 누락");
--}
SELECT 
'if(StringUtils.isBlank('
|| LOWER(TABLE_NAME)
|| '.get'
|| REPLACE(INITCAP(COLUMN_NAME), '_', '')
|| '())){ valid = false; errors.put("'
|| FN_SNAKETOCAMEL(COLUMN_NAME)
|| '", "'
|| COMMENTS
|| ' 누락"); }'
FROM COLS NATURAL JOIN USER_COL_COMMENTS
WHERE TABLE_NAME = 'MEMBER' AND NULLABLE = 'N';
-----------------------------------------------------------------------------------------------------------------------------
DESC MEMBER;







