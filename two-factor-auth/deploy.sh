kubeless function deploy add-defaults \
    --runtime python27 \
    --handler ReqProcess.handler \
    --from-file ReqProcess.py \
    --trigger-http



# Function call
kubeless function call add-defaults --data '{"name": "testusername","email":"test@gmail.com"}'



# 2 factor auth 
kubeless function deploy add-two-factor \
    --runtime python27 \
    --handler ReqProcess.two_factor \
    --from-file ReqProcess.py \
    --trigger-http