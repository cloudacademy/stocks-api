# stocks-api

## Building for amd64 targets on mac arm64 with `docker buildx`

```bash
docker buildx create --use
docker buildx build --platform linux/amd64 --push -t <tag_to_push> .
```