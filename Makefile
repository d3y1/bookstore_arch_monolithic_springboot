CONTAINER = bookstore
TAG = monolithic

REPOSITORY ?= $(DOCKER_USERNAME)/$(CONTAINER)

existed ?= $(shell docker ps -a | grep $(CONTAINER) &>/dev/null && echo "yes" || echo "no")

# -----------------------------------------START------------------------------------------------

# make
all: maven build run
# make start
start: maven build run

maven:
	mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V

build:
	bash docker_build_push.sh

run:
ifeq ($(existed), yes)
	docker stop $(CONTAINER); docker rm $(CONTAINER)
endif
	docker run -d -p 8080:8080 --name $(CONTAINER) $(REPOSITORY):$(TAG)

# -----------------------------------------STOP------------------------------------------------

stop:
	docker stop $(CONTAINER)