load_project_env() {
    if [ ! -f .project_env ]; then
        echo ".project_env not found."
        exit 1
    fi
    eval $(cat .project_env)
}
