import { test, expect } from '@playwright/test';

test('Handle multiple tabs', async ({ page, context }) => {

  await page.goto('https://the-internet.herokuapp.com/windows');

  const [newTab] = await Promise.all([
    context.waitForEvent('page'),
    page.click('a[href="/windows/new"]'),
  ]);

  await expect(newTab.locator('h3')).toHaveText('New Window');
});
