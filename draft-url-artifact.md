name: Publish Artifact and Comment

on:
pull_request:
branches:
- main

jobs:
build_and_comment:
runs-on: ubuntu-latest

    steps:
      - name: Checkout Repository
        uses: actions/checkout@v2

      - name: Build Project
        run: |
          # Your build commands here
          # For example, if using Maven:
          mvn clean install

      - name: Publish Artifact
        id: publish-artifact
        uses: actions/upload-artifact@v2
        with:
          name: my-artifact
          path: path/to/artifact  # Adjust this path based on your project structure

      - name: Get Artifact URL
        id: artifact-url
        run: echo "::set-output name=artifact_url::$(curl -s -H \"Authorization: Bearer ${{ secrets.GITHUB_TOKEN }}\" -X POST -H \"Accept: application/vnd.github.v3+json\" https://api.github.com/repos/${{ github.repository }}/actions/runs/${{ github.run_id }}/artifacts | jq -r '.artifacts[0].archive_download_url')"

      - name: Comment on Pull Request
        uses: actions/github-script@v5
        with:
          github-token: ${{ secrets.GITHUB_TOKEN }}
          script: |
            const artifactUrl = '${{ steps.artifact-url.outputs.artifact_url }}';
            const prNumber = '${{ github.event.pull_request.number }}';
            const repoOwner = '${{ github.repository_owner }}';
            const repoName = '${{ github.repository_name }}';
            const octokit = github.getOctokit('${{ secrets.GITHUB_TOKEN }}');

            await octokit.rest.issues.createComment({
              owner: repoOwner,
              repo: repoName,
              issue_number: prNumber,
              body: `Artifact Link: ${artifactUrl}`,
            });


    - name: Get Artifact URL
      id: artifact-url
      shell: bash
      run: |
        echo "artifact_url=$(curl -s -H \"Authorization:Bearer ${{ inputs.repo-token }}\" -X POST -H \"Accept: application/vnd.github.v3+json\" https://api.github.com/repos/${{ github.repository }}/actions/runs/${{ github.run_id }}/artifacts | jq -r '.artifacts[0].archive_download_url')" >> $GITHUB_OUTPUT
