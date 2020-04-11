# Replicated load balance

<a href="https://imgur.com/YYgNGPN"><img src="https://i.imgur.com/YYgNGPN.png" title="source: imgur.com" /></a>

- Scale up 
- Scale down 
- Kubernetes to deploy stateless, replicated service behind a load balance 
- session tracking 
    - consistent hashing function 
<a href="https://imgur.com/yNgxZ58"><img src="https://i.imgur.com/yNgxZ58.png" title="source: imgur.com" /></a>

### Caching layer 
- caching web proxy 
- use : https://varnish-cache.org/ 
- expand 
- HTTP reverse proxies 
    - rate limiting
    - dos defense 
    - `throttle` module 
    - X-RateLimit-Remaining
<a href="https://imgur.com/5NG9WQ4"><img src="https://i.imgur.com/5NG9WQ4.png" title="source: imgur.com" /></a>


### SSL termination 

### nginx 
- replicated nghinx server 

### Deploy 
- LB 
- Cache : stateless replicated serving tier 
    - varnish cache config 

- nginx & SSL termination 
