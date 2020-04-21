## deploy etcd 
- distributed lock server deployed by CoreOS 
- kubernetes 

## stack 
- helm 
    - helm tools 
- etcd operatir developed by CoreOS 



- Initialize helm
helm init

- Install the etcd operator
helm install stable/etcd-operator

# implement locs in etcd
- mutex 
- distributed lock = distributed key value store

1/ acquire lock 
func simpleLock() :boolean {
    // compare and swap 1 for 0 
    locked, error = compareAndSwap(1.lockName,1,0)
    // lock does not exist, write 1 with prev value
    if error != nil 
        locked, _ = compareAndSwap(1.lockName,1,nil)

    return locked;
}


- watch changes instead of polling 
func(Lock i) : lock(){
    while(!l.simpleLock()) waitForChanges(l.lockName)
}


- TTL of key-value store 


- resource version for write operations 

func(Lock l) simpleLock: boolean {
    // compare & swap 1 - 0 
    locked, l.version, error = compare&Swap 
    ...
    return locked
}

- `etcdctl`
- etcd clients



# implement ownership
- create revewable lock 
- handleLockLost()

# implement leases in etcd 
- kubectl exec my-etcd-cluster-0000 -- \
    sh -c "ETCD_API=3 etcdctl --endpoints=${ETCD_ENDPOINTS} \
        --ttl=10 mk my-lock user1"

<a href="https://imgur.com/NzfnCqL"><img src="https://i.imgur.com/NzfnCqL.png" title="source: imgur.com" /></a>



# handle concurrent data manipulation 


