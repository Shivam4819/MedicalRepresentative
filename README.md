# MedicalRepresentative

MedicalRepresentative Project has 7 api which fullfill different function. The backend is made using Spring Boot . The database which is used is MySQL.

## Api

* insertMR- This api add data of MR to database.

            curl --location --request POST 'http://localhost:8080/insert' \
            --header 'Content-Type: application/json' \
            --data-raw '{
                "empName":"raju",
                "address":"sector-12,mumbai"
            }'
          
 * getAllMR- This api will give details about all MR ,but not the medicine allocated to them.
             
             curl --location --request GET 'http://localhost:8080/getallmr' 
 
 * getParticular- This api will give details about specific MR including all the medicine assigned to that MR.
 
              curl --location --request POST 'http://localhost:8080/getMR' \
              --header 'Content-Type: application/json' \
              --data-raw '{
                  "empID":9
              }'
 
 * deleteMR- This api will delete all the records about the spcific MR , it details and the medicine assigned to the MR will be deleted
 
             curl --location --request POST 'http://localhost:8080/delete' \
            --header 'Content-Type: application/json' \
            --data-raw '{
                "empId":9
            }'
 
 * addAllMed- This api will assign a medicine to particular MR
 
           curl --location --request POST 'http://localhost:8080/addmed' \
          --header 'Content-Type: application/json' \
          --data-raw '{
              "empId":10,
              "medName":"diclogem",
              "price":500
          }'
 
 * updateData- This api will update the medicine records for specific MR.
 
           curl --location --request POST 'http://localhost:8080/update' \
          --header 'Content-Type: application/json' \
          --data-raw '{
              "empid":3,
              "medName":"xylo",
              "updateMedName":"viraday",
              "updatePrice":4000
          }'
 
 * deleteSpecificData- This api will remove a specific medine from specific MR
 
           curl --location --request POST 'http://localhost:8080/delspe' \
          --header 'Content-Type: application/json' \
          --data-raw '{
              "empid":10,
              "med":"diclogem"
          }'
