# What is Caching?
- Caching stores frequently accessed data in a fast storage layer (memory, Redis, etc.) so future requests can be served quickly without recompilation or hitting slower systems (databases, remote APIs). 
## Why Cache?
- Performance: Reduce latency and CPU usage. 
- Cost: Fewer DB calls, less network I/O. 
- Scalability: Higher throughput with same infra.
## Common Caching Strategies 
- Application reads from cache; on miss, loads from source and stores in cache.
- Write-through: Writes go through cache to the source. 
- Cache-aside (lazy loading): App checks cache; if miss, fetches from source, then populates cache (most common in Spring). 
- Invalidate/Evict: Remove entries when data changes to keep cache consistent.

## Spring Boot Caching—The Abstraction 
Spring provides a cache abstraction (org.springframework.cache) that you can enable and use with simple annotations:
- @Cacheable – Cache method results (read).
- @CachePut – Update cache without skipping method execution (write/update). 
- @CacheEvict – Remove from cache (invalidate). 
- @Caching – Combine multiple cache operations. 
- @EnableCaching – Turn on the caching mechanism.

These annotations are provider-agnostic (Caffeine, Redis, Hazelcast, Ehcache, etc.).
Quick Start: Minimal In-Memory Cache (Caffeine) Good for single-node services or small datasets. Fast, with TTL and size limits