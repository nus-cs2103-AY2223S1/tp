// Adapted from https://github.com/w3c/node-linkchecker
import { check } from "node-linkchecker";
const options = {
  schemes: ["https:", "http:"],
  userAgent:
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36",
  fragments: true,
};

const HOST = "http://localhost:4000";

const PATHS_TO_CHECK = ["/", "/UserGuide.html", "/DeveloperGuide.html"];
for (const path of PATHS_TO_CHECK) {
  console.log("Checking URL: " + HOST + path);
  check(HOST + path, options).then(
    ({ brokenLinks, brokenFragments }) => {
      if (brokenLinks.length > 0) {
        console.log("Broken Links:");
        console.log(brokenLinks);
        process.exitCode = 1;
      }
      if (brokenFragments.length > 0) {
        console.log("Broken Fragments:");
        console.log(brokenFragments);
        process.exitCode = 1;
      }
    },
    (err) => {
      console.log(`Error: ${err}`);
    }
  );
}
