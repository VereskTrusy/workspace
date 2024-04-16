-- 2024.04.03
-- 비밀번호 동일하게 바꾸기..암호화되면 모르니깐
UPDATE MEMBER
SET
    MEM_PASS = '{bcrypt}$2a$10$61os8SmHo1ZnfzI3Sk0liuonMKWa2mk1FczoCoDLdy50Zjz/145Ny'
;


-- CartVO 작성
-- 테이블 컬럼 > java 코드로
select 'private '
|| case
    when COL.DATA_TYPE = 'DATE' then 'String '
    when COL.DATA_TYPE = 'NUMBER' then 'Long '
    else 'String '
    end
|| FN_SNAKETOCAMEL(COL.COLUMN_NAME) || '; '
|| '// ' || UCC.COMMENTS
FROM COLS COL
INNER JOIN USER_COL_COMMENTS UCC 
    ON (COL.TABLE_NAME = UCC.TABLE_NAME AND COL.COLUMN_NAME = UCC.COLUMN_NAME)
WHERE 1=1
AND COL.TABLE_NAME = 'CART';


-- Mypage 에서 어떤 상품을 몇개를 구매 했는지 (상품명, 구매량) 출력해주기
-- 한 회원이 구매한 상품 조회하기
SELECT *
FROM MEMBER
INNER JOIN CART
    ON (MEMBER.MEM_ID = CART.CART_MEMBER)
WHERE 1=1
AND MEMBER.MEM_ID = 'a001'
;

SELECT 
    CART.CART_MEMBER
    , CART.CART_PROD
    , SUM(CART.CART_QTY)
FROM MEMBER
INNER JOIN CART
    ON (MEMBER.MEM_ID = CART.CART_MEMBER)
INNER JOIN PROD ON (CART.CART_PROD = PROD.PROD_ID)
GROUP BY CART.CART_MEMBER, CART.CART_PROD
HAVING MEMBER.MEM_ID = 'a001';

WITH MEMCART_VIEW AS(
    SELECT 
        CART.CART_MEMBER
        , CART.CART_PROD
        , SUM(CART.CART_QTY) CART_QTY
    FROM CART
    INNER JOIN PROD ON (CART.CART_PROD = PROD.PROD_ID)
    GROUP BY CART.CART_MEMBER, CART.CART_PROD
)
SELECT *
FROM MEMBER
INNER JOIN MEMCART_VIEW ON MEMBER.MEM_ID = MEMCART_VIEW.CART_MEMBER
INNER JOIN PROD ON MEMCART_VIEW.CART_PROD = PROD.PROD_ID
WHERE MEMBER.MEM_ID = 'a001';

-- a001 사용자의 기본정보와
-- 해당 사용자의 구매기록을 조회(상품명, 구매수량)
SELECT MEM_NAME, PROD_NAME
FROM MEMBER LEFT OUTER JOIN CART ON (MEM_ID = CART_MEMBER)
            LEFT OUTER
            JOIN PROD ON (CART_PROD = PROD_ID)
WHERE MEM_ID = 'a001';

WITH CARTPROD AS (
    SELECT 
    CART_MEMBER
    , CART_NO
    , TO_DATE(SUBSTR(CART_NO, 1, 8), 'YYYYMMDD') CART_DATE /*논리컬럼*/
    , CART_PROD
    , CART_QTY
    , PROD.*
    FROM CART INNER JOIN PROD ON (CART_PROD = PROD_ID)
)
SELECT MEM_NAME, PROD_NAME
FROM MEMBER LEFT OUTER JOIN CARTPROD ON (MEM_ID = CART_MEMBER)
WHERE MEM_ID = 'a001';


-- 상품 등록 전 아이디 구하기
SELECT 
    #{prodLgu} || LPAD(NVL(TO_NUMBER(SUBSTR(MAX(PROD_ID), 5)), 0) + 1, 6, '0')
FROM PROD
WHERE PROD_LGU = #{prodLgu}
;

SELECT 
', ' || COLUMN_NAME
FROM COLS
WHERE 1=1
AND COLS.TABLE_NAME = 'PROD';

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
AND COLS.TABLE_NAME = 'PROD';



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
where table_name = 'MEMBER';


select *
from prod


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
AND COL.TABLE_NAME = 'PROD';



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
|| ' 누락");'
FROM COLS NATURAL JOIN USER_COL_COMMENTS
WHERE TABLE_NAME = 'PROD' AND NULLABLE = 'N';
-----------------------------------------------------------------------------------------------------------------------------








