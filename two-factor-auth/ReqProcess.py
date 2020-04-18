# proof of concept


# Simple handler function for adding default values
def handler(context):
  # Get the input value
  obj = context.json
  # If the 'name' field is not present, set it randomly
  if obj.get("name", None) is None:
    obj["name"] = random_name()

  if obj.get("email", None) is None:
    obj["email"] = random_email()  
  # If the 'color' field is not present, set it to 'blue'
  # Call the actual API, potentially with the new default
  # values, and return the result
  return call_my_api(obj)



# POC for 2 factor 
def two_factor(context):
  # Generate a random six digit code
  code = random.randint(100000, 999999)

  # Register the code with the login service
  user = context.json["user"]
  register_code_with_login_service(user, code)

  # Use the twillio library to send texts
  account = "my-account-sid"
  token = "my-token"
  client = twilio.rest.Client(account, token)

  user_number = context.json["phoneNumber"]
  msg = "Hello {} your authentication code is: {}.".format(user, code)
  message = client.api.account.messages.create(to=user_number,
                                               from_="+12065251212",
                                               body=msg)
  return {"status": "ok"}



def create_user(context):
  # For required event handlers, call them universally
  for key, value in required.items():
    call_function(value.webhook, context.json)

  # For optional event handlers, check and call them
  # conditionally
  for key, value in optional.items():
    if context.json.get(key, None) is not None:
      call_function(value.webhook, context.json)


def email_user(context):
  # Get the user name
  user = context.json['username']
  msg = 'Hello {} thanks for joining my awesome service!".format(user)
  send_email(msg, contex.json['email])


def subscribe_user(context):
  # Get the user name
  email = context.json['email']
  subscribe_user(email)