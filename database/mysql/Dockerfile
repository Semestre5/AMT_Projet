# //DPE - C'est niquel d'utiliser docker-compose pour le dev mais vous avez pas besoin de faire une image pour changer le port vous pouvez le faire dans le compose direct
FROM mysql:8.0.21
ADD database/ /docker-entrypoint-initdb.d
EXPOSE 3306