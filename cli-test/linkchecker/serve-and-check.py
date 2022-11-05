#!/usr/bin/python3
import asyncio
import os
import subprocess
import sys
import time

# FIXME: Hacky global variable
exitstatus = 0

async def main():
    global exitstatus
    exitstatus = os.WEXITSTATUS(os.system("node check-links.js"))

if __name__ == '__main__':
    with subprocess.Popen(['bundle', 'exec', 'jekyll serve'], cwd='../../docs') as p:
        time.sleep(10.0)
        asyncio.get_event_loop().run_until_complete(main())
        p.kill()
    sys.exit(exitstatus)
