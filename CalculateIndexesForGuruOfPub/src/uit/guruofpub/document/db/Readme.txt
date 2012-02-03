Require: MySQL, MySQLAdministrator tool.
Process:
1. Unrar <PubGuruDBCrawlingData_ddmmyyyy.rar>
2. Restore using MySQLAdministrator tool
   Note: Choose destination Schema: New Schema, then enter the name PubGuru.
3. Create tables for ranking: run  <PubGuruDBRankingStructureScript_ddmmyyyy.sql>
4. Generate data for the ranking tables: run <PubGuruDBRankingComputingScript_ddmmyyyy.sql>