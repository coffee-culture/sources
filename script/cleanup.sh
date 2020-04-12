function cleanup() {
	docker-compose --project-name=$PROJECT_NAME stop &> /dev/null || true &> /dev/null
	docker-compose --project-name=$PROJECT_NAME rm --force --remove-orphans &> /dev/null || true &> /dev/null
	docker stop `docker ps -a -q -f status=exited` &> /dev/null || true &> /dev/null
	docker rm -v `docker ps -a -q -f status=exited` &> /dev/null || true &> /dev/null
	docker rmi `docker images --filter 'dangling=true' -q --no-trunc` &> /dev/null || true &> /dev/null
	docker ps
}
trap cleanup EXIT
