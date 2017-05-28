<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" indent="yes" />

<xsl:template match="/">
   <html>
   <body>
   <p><b><xsl:value-of select="//Author" /></b></p>
   <p><b>Contents: </b><xsl:value-of select="//content" /></p>
   <p><b>Paid authors:</b></p>
   <table border="1">
      <th>Author</th>
      <th>ID</th>
      <th>Name</th>
      <th>Origin</th>
      <th>Association</th>
      <xsl:for-each select="Author">
      <xsl:sort select="@name" />
            <tr>
            <td><i><xsl:value-of select="@name" /></i></td>
            <td><xsl:value-of select="dob" /></td>
            <td><xsl:value-of select="address" /></td>
            </tr>
      </xsl:for-each>
   </table>
   </body>
   </html>
</xsl:template>

</xsl:stylesheet>