## Search through large DB 
- index 
- search : cat, dog 
- scatter/ gather pattern 
- parse req - farm out 2 leaf machines 
- leaf sharding 

### sharded document search 

    - parallel req across multiple processes on many machines 
    - scatter/ gather tree - root dispatch load to different nodes 
    - root dynamically redistribute load to assure fast response 

    
### Scaling scatter 