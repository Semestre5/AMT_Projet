#!/bin/bash
#ssh -i ./AMT-DMZ-WORKINPROGRESS.pem WORKINPROGRESS@16.170.194.237

#ssh -i ./AMT-WORKINPROGRESS.pem admin@10.0.1.20


# Establish tunnels to DMZ Zone
ssh -f -N -L 23:10.0.1.20:22 -L 8082:10.0.1.20:8080 WORKINPROGRESS@16.170.194.237 -i ./AMT-DMZ-WORKINPROGRESS.pem

# Establish connection to the applicat server in the private subnet
ssh admin@localhost -p 23 -i ./AMT-WORKINPROGESS.pem

scp -p 23 ./AMT-WORKINPROGESS.pem /C:\Users\axelv\projets\Semestre5\AMT\AMT_Projet\target\ admin@localhost:/var/lib/tomcat9/webapps

scp -P 23 -i ./AMT-WORKINPROGESS.pem -r C:/Users/axelv/projets/Semestre5/AMT/AMT_Projet/ admin@localhost:/home/admin/

# -N ou -f