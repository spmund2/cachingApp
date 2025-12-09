**What is Caching?** <br>
Caching stores frequently accessed data in a fast storage layer (memory, Redis, etc.) so future requests can be served quickly without recomputation or hitting slower systems (databases, remote APIs). 
Why Cache?  
Performance: Reduce latency and CPU usage. 
Cost: Fewer DB calls, less network I/O. 
Scalability: Higher throughput with same infra.  
Common Caching Strategies
Read-through: Application reads from cache; on miss, loads from source and stores in cache. 
Write-through: Writes go through cache to the source. 
Cache-aside (lazy loading): App checks cache; if miss, fetches from source, then populates cache (most common in Spring). 
Invalidate/Evict: Remove entries when data changes to keep cache consistent.  
**Spring Boot Caching—The Abstraction** 
Spring provides a cache abstraction (org.springframework.cache) that you can enable and use with simple annotations:  
@Cacheable – Cache method results (read).
@CachePut – Update cache without skipping method execution (write/update). 
@CacheEvict – Remove from cache (invalidate). 
@Caching – Combine multiple cache operations. 
@EnableCaching – Turn on the caching mechanism.  
These annotations are provider-agnostic (Caffeine, Redis, Hazelcast, Ehcache, etc.).  
Quick Start: Minimal In-Memory Cache (Caffeine) Good for single-node services or small datasets. Fast, with TTL and size limits









[
{
"id": 1,
"name": "Asha Nair",
"email": "asha.nair@example.com",
"department": "Engineering",
"salary": 120000,
"createdAt": "2025-12-03T15:37:49.351277Z",
"updatedAt": "2025-12-03T15:37:49.351277Z"
},
{
"id": 2,
"name": "Ravi Verma",
"email": "ravi.verma@example.com",
"department": "HR",
"salary": 80000,
"createdAt": "2025-12-03T15:37:49.471058Z",
"updatedAt": "2025-12-03T15:37:49.471058Z"
},
{
"id": 3,
"name": "Meera Das",
"email": "meera.das@example.com",
"department": "Finance",
"salary": 95000,
"createdAt": "2025-12-03T15:37:49.481057Z",
"updatedAt": "2025-12-03T15:37:49.481057Z"
}
]


