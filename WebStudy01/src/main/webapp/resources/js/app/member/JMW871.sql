



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


















