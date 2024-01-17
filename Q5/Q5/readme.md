<h1> Documentation for </h1>

<h3> Project Structure </h3>
Models: Contains @Entity classes representing database tables.
Repository: Stores JPA repositories for entities.
Batch: Includes Spring Batch configurations and jobs for CSV to MariaDB migration.
API: Contains controller and service for the API endpoint.
Root Directory: Houses Q5Application, the main Spring Boot application class.

<h3> Data Migration </h3>
CSV Processing: Utilize Spring Batch for reading and processing CSV files.
Normalization: Structure data according to 3NF in MariaDB.

<h3> Database Indexing </h3>
After migration, an index named idx_date_occ is created for the column DATE_OCC.

<h3> API Endpoint </h3>
Path: [POST] /api/crime-records/search
Description: Searches records based on from_date and to_date.
Body:
{
  "from_date": "YYYY-MM-DD HH:MM:SS",
  "to_date": "YYYY-MM-DD HH:MM:SS"
}
Response: Array of records with fields like DR_NO, DATE_RPTD, DATE_OCC, etc.

<h3> Deployment </h3>
Containerize the application using Docker and the image is pushed to Docker Hub.
Deployed with Kubernetes, details are in the resources.yaml file.

<h3> Usage </h3>
**Please put the csv file into Q5/Q5/src/main/resources/static/la_crime_2010_to_2023.csv, Thanks.**
1. cd Q5/Q5 from the directory PartB 
2. Run "minikube start" to start minikube
3. Run "kubectl apply -f resources.yaml"
4. Wait for the pod status to become "RUNNING", check by "kubectl get pods"
5. Send POST requests to http://localhost:8080/api/crime-records/search
