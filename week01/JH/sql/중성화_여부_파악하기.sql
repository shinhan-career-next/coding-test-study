-- 코드를 입력하세요
# UPDATE ANIMAL_INS SET SEX_UPON_INTAKE =
# CASE
#     WHEN SEX_UPON_INTAKE == 'Neutered Male' THEN 'O'
#     WHEN SEX_UPON_INTAKE == 'Spayed Female' THEN 'O'
#     WHEN SEX_UPON_INTAKE == 'Intact Male' THEN 'X'
# END

SELECT ANIMAL_ID,
       NAME,
       CASE
           WHEN SEX_UPON_INTAKE LIKE "%Neutered%" THEN 'O'
           WHEN SEX_UPON_INTAKE LIKE "%Spayed%" THEN 'O'
           ELSE 'X'
       END AS "중성화"
FROM ANIMAL_INS;
