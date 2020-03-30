	#! /bin/bash
	
	export USER1=$(curl \
	    --header "X-Vault-Token: $VAULT_TOKEN" \
	    http://vault:8200/v1/secret/my-secret \
	    | jq '.data.hien')
	
	export USER2=$(curl \
	    --header "X-Vault-Token: $VAULT_TOKEN" \
	    http://vault:8200/v1/secret/my-secret \
    | jq '.data.hiennguyen')

    python main.py
