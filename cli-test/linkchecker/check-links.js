// Adapted from https://github.com/w3c/node-linkchecker
import { check } from "node-linkchecker";
const options = {
  schemes: ["https:", "http:"],
  userAgent:
    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36",
  fragments: true,
};

const HOST = process.env.HOST ?? "http://localhost:4000";
const PATHS_TO_CHECK = [
  // Basic
  "/",
  "/UserGuide.html",
  "/DeveloperGuide.html",
  // Other Pages
  "/AboutUs.html",
  "/Configuration.html",
  "/DevOps.html",
  "/Documentation.html",
  "/Logging.html",
  "/SettingUp.html",
  "/Testing.html",
  // PPP
  "/team/bryanljx.html",
  "/team/eugenetanwl3881.html",
  "/team/ferusel.html",
  "/team/richdom2185.html",
  "/team/yixiann.html",
];

const links = new Set();
const fragments = new Set();
for (const path of PATHS_TO_CHECK) {
  console.log("Checking URL: " + HOST + path);
  const { brokenLinks, brokenFragments } = await check(HOST + path, options);
  brokenLinks.forEach(({ link }) => links.add(link));
  brokenFragments.forEach(({ link }) => fragments.add(link));
}
if (links.size > 0) {
  process.stderr.write("Broken Links: ");
  console.error(links);
  process.exitCode = 1;
}
if (fragments.size > 0) {
  process.stderr.write("Broken Fragments: ");
  console.error(fragments);
  process.exitCode = 1;
}
