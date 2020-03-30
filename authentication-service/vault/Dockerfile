FROM python:3.7
RUN apt-get update && apt-get install -y jq
WORKDIR /app
COPY . .
CMD ./entrypoint.sh 
