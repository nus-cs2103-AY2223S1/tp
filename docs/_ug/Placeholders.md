<!-- markdownlint-disable-file first-line-h1 -->
<!-- markdownlint-disable-file no-inline-html -->
<table>
  <thead>
    <tr>
      <th>Placeholders</th>
      <th>Related Flags</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>INDEX</td>
      <td>id/</td>
      <td markdown="1">{% include_relative _ug/placeholders/INDEX.md %}</td>
    </tr>
    <tr>
      <td>INDEX_LIST</td>
      <td>id/</td>
      <td markdown="1">{% include_relative _ug/placeholders/INDEX_LIST.md %}</td>
    </tr>
    <tr>
      <td>ITEM_NAME</td>
      <td>n/</td>
      <td markdown="1">{% include_relative _ug/placeholders/ITEM_NAME.md %}</td>
    </tr>
    <tr>
      <td>TAG_NAME</td>
      <td>n/</td>
      <td markdown="1">{% include_relative _ug/placeholders/TAG_NAME.md %}</td>
    </tr>
    <tr>
      <td>QUANTITY</td>
      <td>qty/</td>
      <td markdown="1">{% include_relative _ug/placeholders/QUANTITY.md %}</td>
    </tr>
    <tr>
      <td>UNIT</td>
      <td>unit/</td>
      <td>The UNIT is an optional text indicating the unit of an item.<br>UNIT is a short text.<br/><br><strong>IMPORTANT</strong>:<br>Only English characters, numbers, space, and the following symbols are accepted: <br>~`!@#$%^&amp;*()_-+={}[]:;”’&lt;&gt;,.?<br>There is a limit of 20 characters in a UNIT.<br>Leading and trailing spaces will be trimmed<br><br/><strong>Valid Examples</strong>:<br>kg<br>Packets<br><br/><strong>Invalid Examples:</strong><br>Containers (1000 grams)<br>Containers/grams<br>Containers|grams<br>Containers\grams</td>
    </tr>
    <tr>
      <td>BOUGHT_DATE</td>
      <td>bgt/</td>
      <td>The BOUGHT_DATE is an optional date indicating when the item was bought.<br>BOUGHT_DATE is a date in one of the following formats:<br>yyyy-mm-dd<br>dd-mm-yyyy<br/><br><strong>IMPORTANT</strong>:<br>We only accept years less than 10000<br><br/><strong>Valid Examples</strong>:<br>2022-09-01<br>01-09-2022<br>1-9-2022<br><br/><strong>Invalid Examples:</strong><br>01-11-20222<br>01/09/2022<br>40-40-2022<br>1-9-22<br>1-nov-2202</td>
    </tr>
    <tr>
      <td>EXPIRY_DATE</td>
      <td>exp/</td>
      <td>The EXPIRY_DATE is an optional date indicating when the item will expire.<br>EXPIRY_DATE is a date in one of the following formats:<br>yyyy-mm-dd<br>dd-mm-yyyy<br/><br><strong>IMPORTANT</strong>:<br>We only accept years less than 10000<br><br/><strong>Valid Examples</strong>:<br>2022-09-01<br>01-09-2022<br>1-9-2022<br><br/><strong>Invalid Examples:</strong><br>01-11-20222<br>01/09/2022<br>40-40-2022<br>1-9-22<br>1-nov-2202</td>
    </tr>
  </tbody>
</table>
