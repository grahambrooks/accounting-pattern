import { defineConfig } from "vitest/config";

export default defineConfig({
  test: {
    globals: true,
    // The Java side names its tests *Test.java; the TypeScript side matches,
    // so the default *.test.ts / *.spec.ts patterns would find nothing.
    include: ["src/test/typescript/**/*Test.ts"],
  },
});
