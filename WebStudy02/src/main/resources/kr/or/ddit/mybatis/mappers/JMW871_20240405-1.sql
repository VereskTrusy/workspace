-- 2024.04.05
-- PROD 업데이트 쿼리
UPDATE PROD
SET
      PROD_ID = #{prodId}
    , PROD_NAME = #{prodName}
    , PROD_LGU = #{prodLgu}
    , PROD_BUYER = #{prodBuyer}
    , PROD_COST = #{prodCost}
    , PROD_PRICE = #{prodPrice}
    , PROD_SALE = #{prodSale}
    , PROD_OUTLINE = #{prodOutline}
    , PROD_DETAIL = #{prodDetail}
    , PROD_IMG = #{prodImg}
    , PROD_TOTALSTOCK = #{prodTotalstock}
    , PROD_INSDATE = #{prodInsdate}
    , PROD_PROPERSTOCK = #{prodProperstock}
    , PROD_SIZE = #{prodSize}
    , PROD_COLOR = #{prodColor}
    , PROD_DELIVERY = #{prodDelivery}
    , PROD_UNIT = #{prodUnit}
    , PROD_QTYIN = #{prodQtyin}
    , PROD_QTYSALE = #{prodQtysale}
    , PROD_MILEAGE = #{prodMileage}
WHERE
    PROD_ID = #{prodId}
;

-- 테스트
UPDATE PROD
SET
    PROD_NAME = '신규'
WHERE 
    PROD_ID = 'P102000008'
;

-- UPDATE SET 절 만들기
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
WHERE COLS.TABLE_NAME = 'PROD'
;


-- 멤버테이블 권한 컬럼 추가
ALTER TABLE MEMBER
ADD(MEM_ROLE VARCHAR2(15) DEFAULT 'ROLE_USER');

-- c001 관리자 권한 부ㅕ
UPDATE MEMBER
SET
    MEM_ROLE = 'ROLE_ADMIN'
WHERE MEM_ID = 'c001';


















