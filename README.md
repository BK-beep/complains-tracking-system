## Required Environment Variables for the ES container(.env file)

### Passwords
- `ELASTIC_PASSWORD`: Password for the 'elastic' user (at least 6 characters)
- `KIBANA_PASSWORD`: Password for the 'kibana_system' user (at least 6 characters)

### Elasticsearch Stack Configuration
- `STACK_VERSION`: Version of Elastic products (e.g., `8.13.4`)
- `CLUSTER_NAME`: Set the cluster name (e.g., `docker-cluster`)
- `LICENSE`: Set to 'basic' or 'trial' to automatically start the 30-day trial (e.g., `basic`, `trial`)

### Ports
- `ES_PORT`: Port to expose Elasticsearch HTTP API to the host (e.g., `127.0.0.1:9200`)
- `KIBANA_PORT`: Port to expose Kibana to the host (e.g., `5601`, `80`)

### Memory Limit
- `MEM_LIMIT`: Increase or decrease based on the available host memory (in bytes) (e.g., `1073741824`)

### Project Namespace
- `COMPOSE_PROJECT_NAME`: Project namespace (defaults to the current folder name if not set)

### Example:
```
# Password for the 'elastic' user (at least 6 characters)
ELASTIC_PASSWORD=

# Password for the 'kibana_system' user (at least 6 characters)
KIBANA_PASSWORD=

# Version of Elastic products
STACK_VERSION=8.13.4

# Set the cluster name
CLUSTER_NAME=docker-cluster

# Set to 'basic' or 'trial' to automatically start the 30-day trial
LICENSE=basic
#LICENSE=trial

# Port to expose Elasticsearch HTTP API to the host
ES_PORT=127.0.0.1:9200
#ES_PORT=127.0.0.1:9200

# Port to expose Kibana to the host
KIBANA_PORT=5601
#KIBANA_PORT=80

# Increase or decrease based on the available host memory (in bytes)
MEM_LIMIT=1073741824

# Project namespace (defaults to the current folder name if not set)
#COMPOSE_PROJECT_NAME=myproject
```

### Create elastic search index with ngram tokenizer fro full text search:
use the kibana console, or Curl

```
DELETE complaints

PUT /complaints
{
  "settings": {
    "index": {
      "max_ngram_diff": 5  // Set this value to match the difference you want between max_gram and min_gram
    },
    "analysis": {
      "tokenizer": {
        "ngram_tokenizer": {
          "type": "ngram",
          "min_gram": 1,
          "max_gram": 5,  // Adjust as needed
          "token_chars": ["letter", "digit"]
        }
      },
      "analyzer": {
        "ngram_analyzer": {
          "type": "custom",
          "tokenizer": "ngram_tokenizer",
          "filter": ["lowercase"]
        }
      }
    }
  },
  "mappings": {
    "properties": {
      "complaintId": {
        "type": "text",
        "analyzer": "ngram_analyzer",
        "search_analyzer": "standard"
      },
      "from": {
        "properties": {
          "name": {
            "type": "text",
            "analyzer": "ngram_analyzer",
            "search_analyzer": "standard"
          },
          "email": {
            "type": "text",
            "analyzer": "ngram_analyzer",
            "search_analyzer": "standard"
          },
          "phone": {
            "type": "text",
            "analyzer": "ngram_analyzer",
            "search_analyzer": "standard"
          }
        }
      },
      "status": {
        "type": "text",
        "analyzer": "ngram_analyzer",
        "search_analyzer": "standard"
      },
      "source": {
        "type": "text",
        "analyzer": "ngram_analyzer",
        "search_analyzer": "standard"
      }
    }
  }
}



GET complaints/_analyze
{
  "analyzer" : "ngram_analyzer",
  "text":"software"
}

```
